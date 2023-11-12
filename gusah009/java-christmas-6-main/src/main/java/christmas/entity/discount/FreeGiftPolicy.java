package christmas.entity.discount;

import christmas.entity.menu.Drink;
import christmas.entity.menu.Menu;
import christmas.entity.price.Price;

public class FreeGiftPolicy implements BenefitPolicy {

    private static final int POSSIBLE_GET_GIFT_PRICE = 120_000;

    private final Menu freeGift;

    private FreeGiftPolicy(Menu freeGift) {
        this.freeGift = freeGift;
    }

    public static FreeGiftPolicy from(Price totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount.get() < POSSIBLE_GET_GIFT_PRICE) {
            return null;
        }
        return new FreeGiftPolicy(Drink.샴페인);
    }

    @Override
    public String toString() {
        return "증정 이벤트";
    }

    public Price getTotalPrice() {
        return freeGift.getPrice();
    }

    public String getFreeGiftInfo() {
        return freeGift.getMenuName() + " 1개";
    }
}
