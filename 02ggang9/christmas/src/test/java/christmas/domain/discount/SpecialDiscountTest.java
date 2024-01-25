package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.SpecialDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SpecialDiscountTest {

    @Test
    @DisplayName("특별 할인을 성공해야 한다.")
    void 특별_할인을_성공해야_한다() {
        final int specialDiscountDay = 10;
        final int BASE_DISCOUNT = 1000;
        SpecialDiscount specialDiscount = new SpecialDiscount(new SpecialDiscountPolicy());
        OrderSheet orderSheet = new OrderSheet(List.of("타파스"), List.of(3), specialDiscountDay);
        BenefitDetail benefitDetail = new BenefitDetail();

        specialDiscount.calculateDiscountAndSaveDetail(benefitDetail, orderSheet);

        assertThat(benefitDetail.getDetails().containsKey("특별 할인: -")).isTrue();
        assertThat(benefitDetail.getDetails().get("특별 할인: -")).isEqualTo(BASE_DISCOUNT);
    }

    @Test
    @DisplayName("특별 할인을 실패해야 한다.")
    void 특별_할인을_실패해야_한다() {
        final int normalDay = 11;
        SpecialDiscount specialDiscount = new SpecialDiscount(new SpecialDiscountPolicy());
        OrderSheet orderSheet = new OrderSheet(List.of("타파스"), List.of(3), normalDay);
        BenefitDetail benefitDetail = new BenefitDetail();

        specialDiscount.calculateDiscountAndSaveDetail(benefitDetail, orderSheet);

        assertThat(benefitDetail.getDetails().containsKey("특별 할인: -")).isFalse();

    }
}
