package christmas.domain.discounts;

import static christmas.model.Menu.CHAMPAGNE;

public class PresentationEvent {
    private final static int PRESENTATION_PRICE = 120000;
    private final static String PRESENTATION_DISCOUNT_MESSAGE = "증정 이벤트: -25,000원";
    private int discount = 0;

    public PresentationEvent(int totalPrice) {
        if (isOverPresentationPrice(totalPrice)) {
            this.discount = CHAMPAGNE.getPrice();
        }
    }

    public boolean isOverPresentationPrice(int totalPrice) {
        return totalPrice > PRESENTATION_PRICE;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        if (discount > 0) {
            return PRESENTATION_DISCOUNT_MESSAGE;
        }
        return "";
    }
}
