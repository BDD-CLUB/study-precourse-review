package christmas.domain.discountpolicy;

import christmas.domain.OrderSheet;

import java.util.List;

public class WeekdayDiscountPolicy implements DiscountPolicy {
    @Override
    public boolean isSatisfiedBy(OrderSheet orderSheet) {
        List<Integer> days = List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31);
        int dateOfVisit = orderSheet.getDateOfVisit();

        return days.contains(dateOfVisit);
    }
}
