package christmas.domain.discountpolicy;

import christmas.domain.OrderSheet;

public interface DiscountPolicy {

    boolean isSatisfiedBy(OrderSheet orderSheet);
}
