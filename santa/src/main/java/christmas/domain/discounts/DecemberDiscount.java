package christmas.domain.discounts;

import static christmas.utils.MenuCategory.DESSERT;
import static christmas.utils.MenuCategory.MAIN;

import christmas.domain.Customer;
import christmas.domain.DecemberDate;

public class DecemberDiscount {

    private final static int MIN_DISCOUNT_PRICE = 10_000;
    private final static String DISCOUNT_DETAIL_MESSAGE = "<혜택 내역>\n";
    private final static String NOTHING = "없음";
    private int totalDiscount = 0;
    private boolean presentEvent = false;
    DDayDiscount dDayDiscount;
    WeekdayDiscount weekdayDiscount;
    WeekendDiscount weekendDiscount;
    SpecialDiscount specialDiscount;
    PresentationEvent presentationEvent;

    public DecemberDiscount(Customer customer) {
        DecemberDate date = customer.getDate();
        int dessertCount = customer.countCategory(DESSERT);
        int mainCount = customer.countCategory(MAIN);
        int totalPrice = customer.calculateTotalPrice();
        if (applyDecemberDiscount(totalPrice)) {
            initDiscounts(date, dessertCount, mainCount, totalPrice);
            presentEvent = presentationEvent.isOverPresentationPrice(totalPrice);

            this.totalDiscount = dDayDiscount.getDiscount() + weekendDiscount.getDiscount()
                    + weekdayDiscount.getDiscount() + specialDiscount.getDiscount() + presentationEvent.getDiscount();
        }
    }

    private void initDiscounts(DecemberDate date, int dessertCount, int mainCount, int totalPrice) {
        dDayDiscount = new DDayDiscount(date);
        weekendDiscount = new WeekendDiscount(date, mainCount);
        weekdayDiscount = new WeekdayDiscount(date, dessertCount);
        specialDiscount = new SpecialDiscount(date);
        presentationEvent = new PresentationEvent(totalPrice);
    }

    public boolean applyDecemberDiscount(int totalPrice) {
        return totalPrice >= MIN_DISCOUNT_PRICE;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public boolean willPresentEvent() {
        return presentEvent;
    }

    @Override
    public String toString() {
        if (totalDiscount > 0) {
            return DISCOUNT_DETAIL_MESSAGE +
                    dDayDiscount.toString() +
                    weekdayDiscount.toString() +
                    weekendDiscount.toString() +
                    specialDiscount.toString() +
                    presentationEvent.toString();
        }
        return DISCOUNT_DETAIL_MESSAGE + NOTHING;
    }
}
