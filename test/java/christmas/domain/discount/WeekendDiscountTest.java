package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.ChristmasDiscountPolicy;
import christmas.domain.discountpolicy.WeekendDiscountPolicy;
import christmas.domain.menu.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.domain.menu.OrderMenu.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeekendDiscountTest {

    @Test
    @DisplayName("주말 할인에 성공해야 한다.")
    void 주말_할인에_성공해야_한다() {
        int dateOfVisit = 2;
        int BASE_DISCOUNT_PRICE = 2023;
        int mainMenuCount = 3;
        int otherMainMenuCount = 4;
        BenefitDetail benefitDetail = new BenefitDetail();
        OrderSheet orderSheet = new OrderSheet(List.of(SEAFOOD_PASTA.getMenuName(), BARBECUE_RIBS.getMenuName()), List.of(mainMenuCount, otherMainMenuCount), dateOfVisit);
        WeekendDiscount weekendDiscount = new WeekendDiscount(new WeekendDiscountPolicy());

        weekendDiscount.calculateDiscountAndSaveDetail(benefitDetail, orderSheet);

        assertThat(benefitDetail.getDetails().containsKey("주말 할인: -")).isTrue();
        assertThat(benefitDetail.getDetails().get("주말 할인: -")).isEqualTo(BASE_DISCOUNT_PRICE * (mainMenuCount + otherMainMenuCount));
    }

    @Test
    @DisplayName("주말 할인에 실패해야 한다.")
    void 주말_할인에_실패해야_한다() {
        int dateOfVisit = 3;
        int mainMenuCount = 3;
        int otherMainMenuCount = 4;
        BenefitDetail benefitDetail = new BenefitDetail();
        OrderSheet orderSheet = new OrderSheet(List.of(SEAFOOD_PASTA.getMenuName(), BARBECUE_RIBS.getMenuName()), List.of(mainMenuCount, otherMainMenuCount), dateOfVisit);
        WeekendDiscount weekendDiscount = new WeekendDiscount(new WeekendDiscountPolicy());

        weekendDiscount.calculateDiscountAndSaveDetail(benefitDetail, orderSheet);

        assertThat(benefitDetail.getDetails().containsKey("주말 할인: -")).isFalse();
    }

}
