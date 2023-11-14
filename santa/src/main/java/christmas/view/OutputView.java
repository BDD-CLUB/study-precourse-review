package christmas.view;

import christmas.domain.ChristmasPromotion;

public class OutputView {
    private static final String HELLO_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public void printHelloMessage() {
        System.out.println(HELLO_MESSAGE);
    }

    public void printChristmasPromotion(ChristmasPromotion christmasPromotion) {
        System.out.println(christmasPromotion.toString());
    }

}
