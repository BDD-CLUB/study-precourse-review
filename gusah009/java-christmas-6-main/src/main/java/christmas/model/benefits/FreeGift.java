package christmas.model.benefits;

import christmas.entity.menu.Drink;
import christmas.entity.menu.Menu;

import java.util.Optional;

public class FreeGift {

    private static final int POSSIBLE_GET_GIFT_PRICE = 120_000;

    private final Menu freeGift;

    private FreeGift(Menu freeGift) {
        this.freeGift = freeGift;
    }

    public static Optional<FreeGift> from(int totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount < POSSIBLE_GET_GIFT_PRICE) {
            return Optional.empty();
        }
        return Optional.of(new FreeGift(Drink.샴페인));
    }

    @Override
    public String toString() {
        return freeGift.getMenuName() + " 1개";
    }
}
