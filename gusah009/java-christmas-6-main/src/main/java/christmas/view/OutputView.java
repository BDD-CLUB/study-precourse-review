package christmas.view;

import christmas.model.VisitDay;
import christmas.model.benefits.EventPreviewInfo;
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

    public void printEventPreviewInfo(EventPreviewInfo eventPreviewInfo) {
        this.printPriceBeforeDiscount(eventPreviewInfo);
        this.printFreeGift(eventPreviewInfo);
        this.printBenefitInfos(eventPreviewInfo);
        this.printTotalBenefitPrice(eventPreviewInfo);
        this.printExpectedPrice(eventPreviewInfo);
        this.printEventBadge(eventPreviewInfo);
    }

    private void printPriceBeforeDiscount(EventPreviewInfo eventPreviewInfo) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(eventPreviewInfo.getTotalPriceBeforeDiscount() + "원");
        System.out.println();
    }

    private void printFreeGift(EventPreviewInfo eventPreviewInfo) {
        System.out.println("<증정 메뉴>");
        System.out.println(eventPreviewInfo.getFreeGift());
        System.out.println();
    }

    private void printBenefitInfos(EventPreviewInfo eventPreviewInfo) {
        System.out.println("<혜택 내역>");
        System.out.println(eventPreviewInfo.getBenefitInfo());
        System.out.println();
    }

    private void printTotalBenefitPrice(EventPreviewInfo eventPreviewInfo) {
        System.out.println("<총혜택 금액>");
        System.out.println(eventPreviewInfo.getTotalBenefitPriceToString());
        System.out.println();
    }

    private void printExpectedPrice(EventPreviewInfo eventPreviewInfo) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(eventPreviewInfo.getExpectedPrice());
        System.out.println();
    }

    private void printEventBadge(EventPreviewInfo eventPreviewInfo) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventPreviewInfo.getEventBadge());
        System.out.println();
    }
}
