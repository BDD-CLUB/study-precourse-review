package christmas.entity.discount;

import christmas.entity.menu.Dessert;
import christmas.entity.menu.Main;
import christmas.model.VisitDay;
import christmas.model.order.Order;
import org.assertj.core.util.VisibleForTesting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum DiscountPolicy {
    CHRISTMAS_DISCOUNT_POLICY(1, 25) {
        @Override
        protected boolean isDisCountable(LocalDate visitDate) {
            return true;
        }

        @Override
        protected int getDiscountPrice(Order order, VisitDay visitDay) {
            return 1000 + ((visitDay.getDay() - 1) * 100);
        }

        @Override
        public String toString() {
            return "크리스마스 디데이 할인";
        }
    },
    SPECIAL_DISCOUNT_POLICY(1, 31) {
        private static final Set<LocalDate> STAR_DAYS = Set.of(
                LocalDate.of(2023, 12, 3),
                LocalDate.of(2023, 12, 10),
                LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 12, 24),
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 31)
        );

        @Override
        protected boolean isDisCountable(LocalDate visitDate) {
            return STAR_DAYS.contains(visitDate);
        }

        @Override
        protected int getDiscountPrice(Order order, VisitDay visitDay) {
            return 1000;
        }

        @Override
        public String toString() {
            return "특별 할인";
        }
    },
    WEEKDAY_DISCOUNT_POLICY(1, 31) {
        private static final Set<DayOfWeek> WEEKDAYS = Set.of(
                DayOfWeek.SUNDAY,
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY
        );

        @Override
        protected boolean isDisCountable(LocalDate visitDate) {
            DayOfWeek visitDayOfWeek = visitDate.getDayOfWeek();
            return WEEKDAYS.contains(visitDayOfWeek);
        }

        @Override
        protected int getDiscountPrice(Order order, VisitDay visitDay) {
            return order.getMenuCount(Dessert.class) * 2023;
        }

        @Override
        public String toString() {
            return "평일 할인";
        }
    },
    WEEKEND_DISCOUNT_POLICY(1, 31) {
        private static final Set<DayOfWeek> WEEKEND = Set.of(
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY
        );

        @Override
        protected boolean isDisCountable(LocalDate visitDate) {
            DayOfWeek visitDayOfWeek = visitDate.getDayOfWeek();
            return WEEKEND.contains(visitDayOfWeek);
        }

        @Override
        protected int getDiscountPrice(Order order, VisitDay visitDay) {
            return order.getMenuCount(Main.class) * 2023;
        }

        @Override
        public String toString() {
            return "주말 할인";
        }
    },
    ;

    private final LocalDate startDateInclude;
    private final LocalDate endDateInclude;

    DiscountPolicy(int startDayInclude, int endDayInclude) {
        this.startDateInclude = LocalDate.of(2023, 12, startDayInclude);
        this.endDateInclude = LocalDate.of(2023, 12, endDayInclude);
    }

    final public Optional<Integer> getDiscountPriceIfDiscountable(Order order, VisitDay visitDay) {
        if (isDiscountable(visitDay)) {
            int discountPrice = this.getDiscountPrice(order, visitDay);
            if (discountPrice <= 0) {
                return Optional.empty();
            }
            return Optional.of(discountPrice);
        }
        return Optional.empty();
    }

    abstract protected int getDiscountPrice(Order order, VisitDay visitDay);

    abstract protected boolean isDisCountable(LocalDate visitDate);

    @VisibleForTesting
    boolean isDiscountable(VisitDay visitDay) {
        LocalDate visitDate = LocalDate.of(2023, 12, visitDay.getDay());
        if (isDiscountPeriod(visitDate)) {
            return this.isDisCountable(visitDate);
        }
        return false;
    }

    private boolean isDiscountPeriod(LocalDate visitDate) {
        if (visitDate.isEqual(startDateInclude) || visitDate.isEqual(endDateInclude)) {
            return true;
        }
        if (visitDate.isAfter(startDateInclude) && visitDate.isBefore(endDateInclude)) {
            return true;
        }
        return false;
    }

    public static Map<String, Integer> getDiscountInfo(Order order, VisitDay visitDay) {
        Map<String, Integer> discountInfo = new HashMap<>();
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            discountPolicy.getDiscountPriceIfDiscountable(order, visitDay)
                    .ifPresent(discountPrice -> discountInfo.put(discountPolicy.toString(), discountPrice));
        }
        return discountInfo;
    }
}
