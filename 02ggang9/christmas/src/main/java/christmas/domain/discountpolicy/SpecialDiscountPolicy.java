package christmas.domain.discountpolicy;

import christmas.domain.OrderSheet;

import java.util.List;

public class SpecialDiscountPolicy implements DiscountPolicy {
    @Override
    public boolean isSatisfiedBy(OrderSheet orderSheet) {
        List<Integer> days = List.of(3, 10, 17, 24, 25, 31);
        return days.contains(orderSheet.getDateOfVisit());
    }
}
