package christmas.model.benefits;

import org.assertj.core.util.VisibleForTesting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Badge {
    산타(20_000),
    트리(10_000),
    별(5_000),
    ;

    private final int earnablePriceThreashold;

    Badge(int earnablePriceThreashold) {
        this.earnablePriceThreashold = earnablePriceThreashold;
    }

    public static Optional<Badge> from(int totalBenefitPrice) {
        return Arrays.stream(Badge.values()).sorted(new BadgeComparator())
                .filter(badge -> badge.earnablePriceThreashold <= totalBenefitPrice)
                .findFirst();
    }

    @VisibleForTesting
    static class BadgeComparator implements Comparator<Badge> {
        public int compare(Badge o1, Badge o2) {
            return o2.earnablePriceThreashold - o1.earnablePriceThreashold;
        }
    }
}
