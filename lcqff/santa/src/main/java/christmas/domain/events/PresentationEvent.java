package christmas.domain.events;

public class PresentationEvent {
    private final static int PRESENTATION_PRICE = 120_000;
    private final boolean getPresent;

    public PresentationEvent(int totalPrice) {
        this.getPresent = doesGetPresent(totalPrice);
    }

    private boolean doesGetPresent(int totalPrice) {
        return totalPrice > PRESENTATION_PRICE;
    }

    public boolean DoseGetPresent() {
        return getPresent;
    }


}
