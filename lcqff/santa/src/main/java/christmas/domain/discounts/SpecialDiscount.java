package christmas.domain.discounts;

import static christmas.utils.DecemberDay.SUNDAY;
import static christmas.utils.DecemberDay.CHRISTMAS;

import christmas.domain.DecemberDate;

public class SpecialDiscount {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final String SPECIAL_DISCOUNT_MESSAGE = "특별 할인: -%,d원";

    private int discount=0;
    DecemberDate decemberDate;

    public SpecialDiscount(DecemberDate decemberDate) {
        this.decemberDate = decemberDate;
        applySpecialDiscount();
    }

    public void applySpecialDiscount() {
        int firstDayDate = decemberDate.getDate() % 7;
        if (firstDayDate == SUNDAY.getDate() || decemberDate.getDate() == CHRISTMAS.getDate()) {
            discount = SPECIAL_DISCOUNT;
        }
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        if (discount > 0) {
            return SPECIAL_DISCOUNT_MESSAGE.formatted(discount) + '\n';
        }
        return "";
    }

}
