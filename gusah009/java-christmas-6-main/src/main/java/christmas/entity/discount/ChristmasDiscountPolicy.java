package christmas.entity.discount;

import christmas.model.VisitDay;
import christmas.model.order.Order;

import java.time.LocalDate;

public class ChristmasDiscountPolicy extends DiscountPolicy {

    private static final int START_DAY = 1;
    private static final int END_DAY = 25;

    private ChristmasDiscountPolicy(int startDayInclude, int endDayInclude, VisitDay visitDay) {
        super(startDayInclude, endDayInclude, visitDay);
    }

    public static ChristmasDiscountPolicy from(VisitDay visitDay) {
        return new ChristmasDiscountPolicy(START_DAY, END_DAY, visitDay);
    }

    @Override
    protected boolean isDisCountable(LocalDate visitDate) {
        return true;
    }

    @Override
    protected int getDiscountPrice() {
        return 1000 + ((visitDay.getDay() - 1) * 100);
    }

    @Override
    public String toString() {
        return "크리스마스 디데이 할인";
    }
}
