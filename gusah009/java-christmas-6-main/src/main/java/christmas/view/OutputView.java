package christmas.view;

import christmas.model.VisitDay;
import christmas.model.benefits.BenefitsInfo;
import christmas.model.order.Order;

public class OutputView {
    public void printGuideMessage(VisitDay visitDay) {
        System.out.println("12월 " + visitDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printMenu(Order order) {
        System.out.println("<주문 메뉴>");
        System.out.println(order);
        System.out.println();
    }

    public void printPriceBeforeDiscount(BenefitsInfo benefitsInfo) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(benefitsInfo.getTotalPriceBeforeDiscount() + "원");
        System.out.println();
    }

    public void printFreeGift(BenefitsInfo benefitsInfo) {
        System.out.println("<증정 메뉴>");
        System.out.println(benefitsInfo.getFreeGift());
        System.out.println();
    }
}
