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

    public abstract void calculateDiscountAndSaveDetail(BenefitDetail benefitDetail, OrderSheet orderSheet);
}
