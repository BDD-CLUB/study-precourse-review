package christmas.domain.events;

import static christmas.domain.Menu.CHAMPAGNE;

public class DecemberEvent {
    private final static String PRESENTATION_DISCOUNT_MESSAGE = "증정 이벤트: -25,000원";
    private final static String PRESENT_CHAMPAGNE = "샴페인 1개";
    private final static String NOTHING = "없음";
    PresentationEvent presentationEvent;

    public DecemberEvent(int totalPrice) {
        this.presentationEvent = new PresentationEvent(totalPrice);
    }

    public int getDiscount() {
        if (presentationEvent.DoseGetPresent()) {
            return CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public String getPresentationMessage() {
        if (presentationEvent.DoseGetPresent()) {
            return PRESENT_CHAMPAGNE;
        }
        return NOTHING;
    }

    @Override
    public String toString() {
        if (presentationEvent.DoseGetPresent()) {
            return PRESENTATION_DISCOUNT_MESSAGE;
        }
        return "";
    }
}
