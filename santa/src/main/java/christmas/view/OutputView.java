package christmas.view;

import christmas.domain.Customer;
import christmas.domain.DecemberDate;
import christmas.model.Badge;
import christmas.model.Menu;
import christmas.domain.discounts.DecemberDiscount;
import java.util.Map;

public class OutputView {
    private static final String HELLO_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PROMOTION_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";
    private static final String ORDERED_MENU = "%s %d개";
    private static final String TOTAL_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private static final String PRICE = "%,d원\n";
    private final static String PRESENTATION_MESSAGE = "<증정 메뉴>";
    private final static String PRESENT_CHAMPAGNE = "샴페인 1개";
    private final static String NOTHING = "없음";
    private final static String TOTAL_DISCOUNT_MESSAGE = "<총혜택 금액>";
    private final static String DISCOUNTED_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private final static String BADGE_MESSAGE = "<12월 이벤트 배지>";

    public void printHelloMessage() {
        System.out.println(HELLO_MESSAGE);
    }

    public void printChristmasPromotion(Customer customer) {
        Map<Menu, Integer> foodWithAmount = customer.getOrderedMenu();
        printPromotionMessage(customer.getDate());
        printOrderedMenu(foodWithAmount);
        printTotalPrice(customer.calculateTotalPrice());
    }

    private void printPromotionMessage(DecemberDate date) {
        System.out.printf(PROMOTION_PREVIEW_MESSAGE, date.getDate());
    }

    private void printOrderedMenu(Map<Menu, Integer> foodWithAmount) {
        System.out.println('\n' + ORDERED_MENU_MESSAGE);
        foodWithAmount.forEach((key, value) -> System.out.printf(ORDERED_MENU + '\n', key.getFoodName(), value));
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println('\n' + TOTAL_PRICE_MESSAGE);
        System.out.printf(PRICE, totalPrice);
    }

    public void printPresentationMenu(boolean isWillPresentation) {
        System.out.println('\n' + PRESENTATION_MESSAGE);
        if (isWillPresentation) {
            System.out.println(PRESENT_CHAMPAGNE);
            return;
        }
        System.out.println(NOTHING);
    }


    public void printDiscountDetails(DecemberDiscount decemberDiscount, int totalPrice) {
        System.out.println('\n' + decemberDiscount.toString());
        int totalDiscount = decemberDiscount.getTotalDiscount();
        printTotalDiscount(totalDiscount);
        printDiscountedPrice(totalPrice, totalDiscount);
    }

    private void printTotalDiscount(int totalDiscount) {
        System.out.println('\n' + TOTAL_DISCOUNT_MESSAGE);
        System.out.printf(PRICE, -1 * totalDiscount);
    }

    public void printDiscountedPrice(int totalPrice, int totalDiscount) {
        System.out.println('\n' + DISCOUNTED_PRICE_MESSAGE);
        System.out.printf(PRICE, totalPrice - totalDiscount);
    }

    public void printBadge(String badgeName) {
        System.out.println('\n' + BADGE_MESSAGE);
        if (badgeName == null) {
            System.out.println(NOTHING);
            return;
        }
        System.out.println(badgeName);
    }
}
