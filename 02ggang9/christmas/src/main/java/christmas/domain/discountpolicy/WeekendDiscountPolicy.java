package christmas.domain.discountpolicy;

import christmas.domain.OrderSheet;

import java.util.List;

public class WeekendDiscountPolicy implements DiscountPolicy {
    @Override
    public boolean isSatisfiedBy(OrderSheet orderSheet) {
        List<Integer> days = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
        return days.contains(orderSheet.getDateOfVisit());
    }
}
