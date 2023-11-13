package christmas.global;

public enum OutputMessage {

    EVENT_PREVIEW("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_PRICE_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIVE_MENU("<증정 메뉴>"),
    EVENT_DETAILS("<혜택 내역>"),
    NOTING("없음"),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
