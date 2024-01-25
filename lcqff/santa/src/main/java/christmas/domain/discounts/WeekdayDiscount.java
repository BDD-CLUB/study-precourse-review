package christmas.domain.discounts;

import christmas.domain.DecemberDate;

public class WeekdayDiscount {

    private static final int DESSERT_DISCOUNT = 2023;
    private static final String WEEKDAY_DISCOUNT_MESSAGE = "평일 할인: -%,d원";

    private int discount = 0;
    DecemberDate decemberDate;

    public WeekdayDiscount(DecemberDate decemberDate, int dessertCount) {
        this.decemberDate = decemberDate;
        applyWeekdayDiscount(dessertCount);
    }

    public void applyWeekdayDiscount(int dessertCount) {
        if (!decemberDate.isWeekend()) {
            this.discount += dessertCount * DESSERT_DISCOUNT;
        }
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        if (discount > 0) {
            return WEEKDAY_DISCOUNT_MESSAGE.formatted(discount) + '\n';
        }
        return "";
    }
}
