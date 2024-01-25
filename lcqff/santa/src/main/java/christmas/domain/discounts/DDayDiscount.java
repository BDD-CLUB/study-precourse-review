package christmas.domain.discounts;

import christmas.domain.DecemberDate;

public class DDayDiscount {
    private static final int D_DAY_DISCOUNT = 100;
    private static final int D_DAY_DISCOUNT_START = 1000;
    private static final String D_DAY_DISCOUNT_MESSAGE = "크리스마스 디데이 할인: -%,d원";
    private int discount=0;
    DecemberDate decemberDate;

    public DDayDiscount(DecemberDate decemberDate) {
        this.decemberDate = decemberDate;
        applyDDayDiscount();
    }

    private void applyDDayDiscount() {
        if (decemberDate.isBeforeChristmas()) {
            discount = D_DAY_DISCOUNT_START + (decemberDate.getDate()-1)*D_DAY_DISCOUNT;
        }
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        if (discount > 0) {
            return D_DAY_DISCOUNT_MESSAGE.formatted(discount) + '\n';
        }
        return "";
    }
}
