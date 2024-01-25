package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.DiscountPolicy;

import java.util.Arrays;
import java.util.List;

public abstract class Discount {
    protected List<DiscountPolicy> policies;

    public Discount(DiscountPolicy ... policies) {
        this.policies = Arrays.asList(policies);
    }

    public void calculateDiscountAndSaveDetail(BenefitDetail benefitDetail, OrderSheet orderSheet) {
        for (DiscountPolicy policy : policies) {
            if (policy.isSatisfiedBy(orderSheet)) {
                calculateAndSave(benefitDetail, orderSheet);
            }
        }
    }

    abstract protected void calculateAndSave(BenefitDetail benefitDetail, OrderSheet orderSheet);
}
