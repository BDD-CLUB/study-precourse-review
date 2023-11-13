package christmas.domain.event;

import christmas.domain.OrderSheet;
import christmas.domain.discountpolicy.DiscountPolicy;

public class GiveMenuDiscountPolicy implements DiscountPolicy {
    @Override
    public boolean isSatisfiedBy(OrderSheet orderSheet) {
        return orderSheet.calculateTotalPrice() >= 25000;
    }
}
