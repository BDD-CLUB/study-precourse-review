package christmas.domain.discounts;

import christmas.domain.DecemberDate;

public class WeekendDiscount {
    private static final int MAIN_DISCOUNT = 2023;
    private static final String WEEKEND_DISCOUNT_MESSAGE = "주말 할인: -%,d원";

    private int discount = 0;
    DecemberDate decemberDate;

    public WeekendDiscount(DecemberDate decemberDate, int mainCount) {
        this.decemberDate = decemberDate;
        applyWeekendDiscount(mainCount);
    }

    public void applyWeekendDiscount(int mainCount) {
        if (decemberDate.isWeekend()) {
            this.discount = mainCount * MAIN_DISCOUNT;
        }
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        if (discount > 0) {
            return WEEKEND_DISCOUNT_MESSAGE.formatted(discount) + '\n';
        }
        return "";
    }

}
