package christmas.entity.discount;

import christmas.model.VisitDay;
import christmas.model.order.Order;
import org.assertj.core.util.VisibleForTesting;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class DiscountPolicy implements BenefitPolicy {

    protected final LocalDate startDateInclude;
    protected final LocalDate endDateInclude;
    protected final VisitDay visitDay;

    DiscountPolicy(int startDayInclude, int endDayInclude, VisitDay visitDay) {
        this.startDateInclude = LocalDate.of(2023, 12, startDayInclude);
        this.endDateInclude = LocalDate.of(2023, 12, endDayInclude);
        this.visitDay = visitDay;
    }

    final public Optional<Integer> getDiscountPriceIfDiscountable() {
        if (isDiscountable()) {
            int discountPrice = this.getDiscountPrice();
            if (discountPrice <= 0) {
                return Optional.empty();
            }
            return Optional.of(discountPrice);
        }
        return Optional.empty();
    }

    abstract protected int getDiscountPrice();

    abstract protected boolean isDisCountable(LocalDate visitDate);

    @VisibleForTesting
    boolean isDiscountable() {
        LocalDate visitDate = LocalDate.of(2023, 12, this.visitDay.getDay());
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
}
