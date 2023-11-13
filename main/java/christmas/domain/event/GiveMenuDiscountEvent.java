package christmas.domain.event;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discount.Discount;
import christmas.domain.discountpolicy.DiscountPolicy;

public class GiveMenuDiscountEvent extends Discount {

    private static final String DISCOUNT_NAME = "증정 이벤트: -";
    private static final int BASE_DISCOUNT_AMOUNT = 25_000;

    public GiveMenuDiscountEvent(DiscountPolicy... policies) {
        super(policies);
    }

    @Override
    public void calculateDiscountAndSaveDetail(BenefitDetail benefitDetail, OrderSheet orderSheet) {
        for (DiscountPolicy policy : policies) {
            if (policy.isSatisfiedBy(orderSheet)) {
                benefitDetail.saveEvent(DISCOUNT_NAME, discountPrice(orderSheet));
            }
        }
    }

    private int discountPrice(OrderSheet orderSheet) {
        return BASE_DISCOUNT_AMOUNT;
    }
}
