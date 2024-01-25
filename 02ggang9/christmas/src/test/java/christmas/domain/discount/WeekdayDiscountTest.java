package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.WeekdayDiscountPolicy;
import christmas.domain.menu.OrderMenu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.domain.menu.OrderMenu.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class WeekdayDiscountTest {

    @Test
    @DisplayName("평일 할인을 성공해야 한다.")
    void 평일_할인을_성공해야_한다() {
        int weekdayDiscountDay = 4;
        int chocoCount = 3;
        int iceCreamCount = 4;
        int discountPrice = 2023;
        BenefitDetail benefitDetail = new BenefitDetail();
        OrderSheet orderSheet = new OrderSheet(List.of(CHOCOLATE_CAKE.getMenuName(), ICE_CREAM.getMenuName()), List.of(chocoCount, iceCreamCount), weekdayDiscountDay);
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(new WeekdayDiscountPolicy());

        weekdayDiscount.calculateDiscountAndSaveDetail(benefitDetail, orderSheet);

        assertThat(benefitDetail.getDetails().containsKey("평일 할인: -")).isTrue();
        assertThat(benefitDetail.getDetails().get("평일 할인: -")).isEqualTo(discountPrice * (chocoCount + iceCreamCount));
    }

    @Test
    @DisplayName("평일 할인에 실패해야 한다.")
    void 평일_할인에_실패해야_한다() {
        int day = 9;
        int mainCount = 3;
        int drinkCount = 4;
        BenefitDetail benefitDetail = new BenefitDetail();
        OrderSheet orderSheet = new OrderSheet(List.of(TBONE_STEAK.getMenuName(), ZERO_COKE.getMenuName()), List.of(mainCount, drinkCount), day);
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(new WeekdayDiscountPolicy());

        weekdayDiscount.calculateDiscountAndSaveDetail(benefitDetail, orderSheet);

        assertThat(benefitDetail.getDetails().containsKey("평일 할인: -")).isFalse();
    }

}
