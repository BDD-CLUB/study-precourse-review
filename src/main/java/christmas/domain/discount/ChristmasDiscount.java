package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.DiscountPolicy;

public class ChristmasDiscount extends Discount{

    private static final Integer BASE_DISCOUNT_AMOUNT = 1000;
    private static final String DISCOUNT_NAME = "크리스마스 디데이 할인: -";

    public ChristmasDiscount(DiscountPolicy... policies) {
        super(policies);
    }

    private int discountPrice(int dateOfVisit) {
        return BASE_DISCOUNT_AMOUNT + ((dateOfVisit-1) * 100);
    }

    @Override
    protected void calculateAndSave(BenefitDetail benefitDetail, OrderSheet orderSheet) {
        benefitDetail.saveEvent(DISCOUNT_NAME, discountPrice(orderSheet.getDateOfVisit()));
    }
}
