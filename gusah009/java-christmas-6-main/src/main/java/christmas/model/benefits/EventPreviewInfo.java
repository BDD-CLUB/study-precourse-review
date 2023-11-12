package christmas.model.benefits;

import christmas.entity.discount.FreeGiftPolicy;
import christmas.model.VisitDay;
import christmas.model.order.Order;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EventPreviewInfo {

    private static final int POSSIBLE_EVENT_PRICE = 10_000;

    private final int totalPriceBeforeDiscount;
    private final BenefitInfo benefitsInfo;
    private final Optional<Badge> eventBadge;

    private EventPreviewInfo(int totalPriceBeforeDiscount, BenefitInfo benefitsInfo, Optional<Badge> eventBadge) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.benefitsInfo = benefitsInfo;
        this.eventBadge = eventBadge;
    }

    public static EventPreviewInfo from(Order order, VisitDay visitDay) {
        int totalPriceBeforeDiscount = order.getTotalPrice();
        if (totalPriceBeforeDiscount < POSSIBLE_EVENT_PRICE) {
            return new EventPreviewInfo(totalPriceBeforeDiscount, BenefitInfo.empty(), Optional.empty());
        }
        FreeGiftPolicy freeGift = FreeGiftPolicy.from(totalPriceBeforeDiscount);
        BenefitInfo benefitsInfo = BenefitInfo.from(order, visitDay, freeGift);
        Optional<Badge> eventBadge = Badge.from(benefitsInfo.getTotalBenefitPrice());
        return new EventPreviewInfo(totalPriceBeforeDiscount, benefitsInfo, eventBadge);
    }

    public String getTotalPriceBeforeDiscount() {
        return NumberFormat.getInstance().format(totalPriceBeforeDiscount);
    }

    public String getFreeGift() {
        return benefitsInfo.getFreeGift();
    }

    public String getBenefitInfo() {
        return benefitsInfo.getBenefitInfo();
    }

    public String getTotalBenefitPriceToString() {
        return NumberFormat.getInstance().format(-this.benefitsInfo.getTotalBenefitPrice()) + "원";
    }

    public String getExpectedPrice() {
        return NumberFormat.getInstance().format(this.totalPriceBeforeDiscount - this.benefitsInfo.getTotalDiscountPrice()) + "원";
    }

    public String getEventBadge() {
        return eventBadge.map(Badge::name).orElse("없음");
    }
}
