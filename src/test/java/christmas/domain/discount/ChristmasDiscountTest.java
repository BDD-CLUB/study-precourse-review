package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.ChristmasDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ChristmasDiscountTest {

    @Test
    @DisplayName("크리스마스 할인을 성공해야 한다.")
    void 크리스마스_할인을_성공해야_한다() {
        int dateOfVisit = 3;
        int expectedDiscountPrice = 900 + (dateOfVisit * 100);
        BenefitDetail benefitDetail = new BenefitDetail();
        OrderSheet orderSheet = new OrderSheet(List.of("타파스"), List.of(3), dateOfVisit);
        ChristmasDiscount christmasDiscount = new ChristmasDiscount(new ChristmasDiscountPolicy());

        christmasDiscount.calculateDiscountAndSaveDetail(benefitDetail, orderSheet);

        assertThat(benefitDetail.getDetails().containsKey("크리스마스 디데이 할인: -")).isTrue();
        assertThat(benefitDetail.getDetails().get("크리스마스 디데이 할인: -")).isEqualTo(expectedDiscountPrice);
    }

}
