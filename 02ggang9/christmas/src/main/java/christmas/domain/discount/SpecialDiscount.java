package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.DiscountPolicy;

public class SpecialDiscount extends Discount {

    private static final String DISCOUNT_NAME = "특별 할인: -";
    private static final int BASE_DISCOUNT_AMOUNT = 1000;

    public SpecialDiscount(DiscountPolicy... policies) {
        super(policies);
    }

    @Override
    protected void calculateAndSave(BenefitDetail benefitDetail, OrderSheet orderSheet) {
        benefitDetail.saveEvent(DISCOUNT_NAME, discountPrice(orderSheet));
    }

    private int discountPrice(OrderSheet orderSheet) {
        return BASE_DISCOUNT_AMOUNT;
    }
}
