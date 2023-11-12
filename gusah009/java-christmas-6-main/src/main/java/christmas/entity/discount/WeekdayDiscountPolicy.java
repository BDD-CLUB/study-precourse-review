package christmas.entity.discount;

import christmas.entity.price.Price;
import christmas.model.VisitDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class WeekdayDiscountPolicy extends DiscountPolicy {

    private static final Set<DayOfWeek> WEEKDAYS = Set.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY
    );
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private final int dessertMenuCount;

    private WeekdayDiscountPolicy(int startDayInclude, int endDayInclude, VisitDay visitDay, int dessertMenuCount) {
        super(startDayInclude, endDayInclude, visitDay);
        this.dessertMenuCount = dessertMenuCount;
    }

    public static WeekdayDiscountPolicy from(VisitDay visitDay, int dessertMenuCount) {
        return new WeekdayDiscountPolicy(START_DAY, END_DAY, visitDay, dessertMenuCount);
    }

    @Override
    protected boolean isDisCountable(LocalDate visitDate) {
        DayOfWeek visitDayOfWeek = visitDate.getDayOfWeek();
        return WEEKDAYS.contains(visitDayOfWeek);
    }

    @Override
    protected Price getDiscountPrice() {
        return Price.from(dessertMenuCount * 2023);
    }

    @Override
    public String toString() {
        return "평일 할인";
    }
}
