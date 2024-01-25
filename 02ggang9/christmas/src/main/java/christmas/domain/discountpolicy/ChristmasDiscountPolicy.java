package christmas.domain.discountpolicy;

import christmas.domain.OrderSheet;

import java.util.List;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    @Override
    public boolean isSatisfiedBy(OrderSheet orderSheet) {
        int dateOfVisit = orderSheet.getDateOfVisit();
        return dateOfVisit >= 1 && dateOfVisit <= 31;
    }
}
