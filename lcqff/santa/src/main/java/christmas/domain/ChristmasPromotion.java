package christmas.domain;

import christmas.domain.discounts.DecemberDiscount;
import christmas.domain.events.DecemberEvent;

public class ChristmasPromotion {
    private static final String PROMOTION_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private final static String PRESENTATION_MESSAGE = "<증정 메뉴>";
    private final static String DISCOUNT_DETAIL_MESSAGE = "<혜택 내역>";
    private final static String TOTAL_DISCOUNT_MESSAGE = "<총혜택 금액>";
    private final static String DISCOUNTED_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private final static String BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final String PRICE = "%,d원\n";

    DecemberDiscount decemberDiscount;
    DecemberEvent decemberEvent;
    Customer customer;
    int totalDiscount;

    public ChristmasPromotion(Customer customer) {
        this.customer = customer;
        this.decemberDiscount = new DecemberDiscount(customer);
        this.decemberEvent = new DecemberEvent(customer.getTotalPrice());
        this.totalDiscount = calculateTotalDiscount();
        customer.assignBadge(totalDiscount);
    }

    private int calculateTotalDiscount() {
        return decemberDiscount.getDiscount() + decemberEvent.getDiscount();
    }

    private String getDiscountDetail() {
        return decemberDiscount.toString() + decemberEvent.toString();
    }

    private int calculateDiscountedPrice() {
        return customer.getTotalPrice() - totalDiscount;
    }

    @Override
    public String toString() {
        return PROMOTION_PREVIEW_MESSAGE.formatted(customer.getDate().getDate()) + "\n" +
                ORDERED_MENU_MESSAGE + "\n" +
                customer.getOrderedMenu() +
                TOTAL_PRICE_MESSAGE + "\n" +
                PRICE.formatted(customer.getTotalPrice()) +
                PRESENTATION_MESSAGE + "\n" +
                decemberEvent.getPresentationMessage() + "\n" +
                DISCOUNT_DETAIL_MESSAGE + "\n" +
                getDiscountDetail() + "\n" +
                TOTAL_DISCOUNT_MESSAGE + "\n" +
                PRICE.formatted(-1 * totalDiscount) + "\n" +
                DISCOUNTED_PRICE_MESSAGE + "\n" +
                PRICE.formatted(calculateDiscountedPrice()) + "\n" +
                BADGE_MESSAGE + "\n" +
                customer.getBadgeName();
    }
}
