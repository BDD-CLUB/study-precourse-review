package christmas.domain.badge;

import java.util.function.Function;

public enum Badge {

    SANTA("산타", (price) -> price >= 20_000),
    TREE("트리", (price) -> price >= 10_000 && price < 20_000),
    START("별", (price) -> price >= 5_000 && price < 10_000),
    NOTING("없음", (price) -> price >= 0 && price < 5_000),
    ;

    private final String badgeName;
    private final Function<Integer, Boolean> function;

    Badge(String badgeName, Function<Integer, Boolean> function) {
        this.badgeName = badgeName;
        this.function = function;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public Function<Integer, Boolean> getFunction() {
        return function;
    }
}
