package christmas.entity.discount;

import christmas.model.VisitDay;

import java.time.LocalDate;
import java.util.Set;

public class SpecialDiscountPolicy extends DiscountPolicy {

    private static final Set<LocalDate> STAR_DAYS = Set.of(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31)
    );
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private SpecialDiscountPolicy(int startDayInclude, int endDayInclude, VisitDay visitDay) {
        super(startDayInclude, endDayInclude, visitDay);
    }

    public static SpecialDiscountPolicy from(VisitDay visitDay) {
        return new SpecialDiscountPolicy(START_DAY, END_DAY, visitDay);
    }

    @Override
    protected boolean isDisCountable(LocalDate visitDate) {
        return STAR_DAYS.contains(visitDate);
    }

    @Override
    protected int getDiscountPrice() {
        return 1000;
    }

    @Override
    public String toString() {
        return "특별 할인";
    }
}
