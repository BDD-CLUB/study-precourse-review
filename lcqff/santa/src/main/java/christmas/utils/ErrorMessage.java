package christmas.utils;

public enum ErrorMessage {
    OUT_OF_RANGE_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_ORDERS_DRINK_ERROR("음료수만 주문할 수 없습니다."),
    ORDER_LIMIT_OVER_ERROR("한번에 20개 이하의 메뉴만 주문 가능합니다.");
    private static final String ERROR = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR + message;
    }
}
