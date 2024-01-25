package christmas.domain;

import christmas.domain.events.BadgeEvent;
import christmas.utils.MenuCategory;

public class Customer {
    private BadgeEvent badge;
    private final OrderInfo orderInfo;

    public Customer(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public void assignBadge(int totalDiscount){
        for (BadgeEvent badge : BadgeEvent.values()) {
            String badgeName = badge.getBadge(totalDiscount);
            if (badgeName != null) this.badge = badge;
        }
    }

    public int countCategory(MenuCategory category) {
        return orderInfo.countCategory(category);
    }

    public String getBadgeName() {
        if (badge == null) return "없음";
        return badge.name();
    }

    public int getTotalPrice() {
        return orderInfo.getTotalPrice();
    }

    public DecemberDate getDate() {
        return orderInfo.getDate();
    }

    public String getOrderedMenu() {
        return orderInfo.toString();
    }
}
