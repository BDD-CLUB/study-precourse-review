package christmas.domain.detail;

import christmas.domain.OrderSheet;
import christmas.domain.menu.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.domain.menu.OrderMenu.*;
import static org.assertj.core.api.Assertions.*;

class BenefitDetailTest {

    @Test
    @DisplayName("성공적으로 이벤트 정보들을 저장해야 한다")
    void 성공적으로_이벤트_정보들을_저장해야_한다() {
        int expectedDiscountPrice = 8269;
        BenefitDetail benefitDetail = new BenefitDetail();
        OrderSheet orderSheet = new OrderSheet(List.of(CHOCOLATE_CAKE.getMenuName(), TBONE_STEAK.getMenuName()), List.of(3,4), 3);

        benefitDetail.saveEventDetails(orderSheet);

        assertThat(benefitDetail.getTotalBenefitPrice()).isEqualTo(expectedDiscountPrice);
    }

    @Test
    @DisplayName("성공적으로 이벤트 정보를 저장해야 한다")
    void 성공적으로_이벤트_정보를_저장해야_한다() {
        BenefitDetail benefitDetail = new BenefitDetail();

        benefitDetail.saveEvent("특별 할인: -", 1000);

        assertThat(benefitDetail.getDetails().containsKey("특별 할인: -")).isTrue();
        assertThat(benefitDetail.getDetails().get("특별 할인: -")).isEqualTo(1000);
    }

    @Test
    @DisplayName("성공적으로 이벤트 굿즈를 저장해야 한다")
    void 성공적으로_이벤트_굿즈를_저장해야_한다() {
        BenefitDetail benefitDetail = new BenefitDetail();
        BenefitDetail otherBenefitDetail = new BenefitDetail();

        benefitDetail.setEventGoods(150_000);
        otherBenefitDetail.setEventGoods(3_000);

        assertThat(benefitDetail.getEvent()).isEqualTo(Event.CHAMPAGNE);
        assertThat(otherBenefitDetail.getEvent()).isEqualTo(Event.NOTING);
    }

}
