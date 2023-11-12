package christmas.entity.discount;

import christmas.entity.menu.Main;
import christmas.entity.price.Price;
import christmas.model.VisitDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class WeekendDiscountPolicy extends DiscountPolicy {

    private static final Set<DayOfWeek> WEEKEND = Set.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    );
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private final int mainMenuCount;

    private WeekendDiscountPolicy(int startDayInclude, int endDayInclude, VisitDay visitDay, int mainMenuCount) {
        super(startDayInclude, endDayInclude, visitDay);
        this.mainMenuCount = mainMenuCount;
    }

    public static WeekendDiscountPolicy from(VisitDay visitDay, int mainMenuCount) {
        return new WeekendDiscountPolicy(START_DAY, END_DAY, visitDay, mainMenuCount);
    }

    @Override
    protected boolean isDisCountable(LocalDate visitDate) {
        DayOfWeek visitDayOfWeek = visitDate.getDayOfWeek();
        return WEEKEND.contains(visitDayOfWeek);
    }

    @Override
    protected Price getDiscountPrice() {
        return Price.from(mainMenuCount * 2023);
    }

    @Override
    public String toString() {
        return "주말 할인";
    }
}
