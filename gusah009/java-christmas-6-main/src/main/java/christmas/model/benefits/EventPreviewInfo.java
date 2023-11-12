package christmas.model.benefits;

import christmas.entity.discount.FreeGiftPolicy;
import christmas.entity.price.Price;
import christmas.model.VisitDay;
import christmas.model.order.Order;
import org.jetbrains.annotations.Nullable;

public class EventPreviewInfo {

    private static final int POSSIBLE_EVENT_PRICE = 10_000;

    private final Price totalPriceBeforeDiscount;
    private final BenefitInfo benefitsInfo;
    @Nullable
    private final Badge eventBadge;

    private EventPreviewInfo(Price totalPriceBeforeDiscount, BenefitInfo benefitsInfo, Badge eventBadge) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.benefitsInfo = benefitsInfo;
        this.eventBadge = eventBadge;
    }

    public static EventPreviewInfo from(Order order, VisitDay visitDay) {
        Price totalPriceBeforeDiscount = order.getTotalPrice();
        if (totalPriceBeforeDiscount.get() < POSSIBLE_EVENT_PRICE) {
            return new EventPreviewInfo(totalPriceBeforeDiscount, BenefitInfo.empty(), null);
        }
        FreeGiftPolicy freeGift = FreeGiftPolicy.from(totalPriceBeforeDiscount);
        BenefitInfo benefitsInfo = BenefitInfo.from(order, visitDay, freeGift);
        Badge eventBadge = Badge.from(benefitsInfo.getTotalBenefitPrice()).orElse(null);
        return new EventPreviewInfo(totalPriceBeforeDiscount, benefitsInfo, eventBadge);
    }

    public Price getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public String getFreeGift() {
        return benefitsInfo.getFreeGift();
    }

    public String getBenefitInfo() {
        return benefitsInfo.getBenefitInfo();
    }

    public Price getTotalBenefitPrice() {
        return this.benefitsInfo.getTotalBenefitPrice();
    }

    public Price getExpectedPrice() {
        return this.totalPriceBeforeDiscount.minus(this.benefitsInfo.getTotalDiscountPrice());
    }

    public String getEventBadge() {
        if (eventBadge == null) {
            return "없음";
        }
        return eventBadge.name();
    }
}
