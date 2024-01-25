package christmas.domain.menu;

import java.util.Arrays;
import java.util.function.Function;

public enum Event {

    CHAMPAGNE("샴페인", 25_000, (amount) -> amount > 120_000),
    NOTING("없음", 0, (amount) -> amount > 0),
    ;

    private final String giveMenuName;
    private final Integer discountPrice;
    private final Function<Integer, Boolean> function;

    Event(String giveMenuName, Integer discountPrice, Function<Integer, Boolean> function) {
        this.giveMenuName = giveMenuName;
        this.discountPrice = discountPrice;
        this.function = function;
    }

    public String getGiveMenuName() {
        return giveMenuName;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public Function<Integer, Boolean> getFunction() {
        return function;
    }

    public static Event getGiveMenu(Integer totalPrice) {
        return Arrays.stream(Event.values())
                .filter(event -> event.function.apply(totalPrice))
                .findFirst()
                .orElse(NOTING);
    }
}
