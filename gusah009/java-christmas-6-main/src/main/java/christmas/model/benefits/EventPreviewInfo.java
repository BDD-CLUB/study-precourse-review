package christmas.model.benefits;

import christmas.entity.discount.FreeGiftPolicy;
import christmas.model.VisitDay;
import christmas.model.order.Order;
import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.util.Optional;

public class EventPreviewInfo {

    private static final int POSSIBLE_EVENT_PRICE = 10_000;

    private final int totalPriceBeforeDiscount;
    private final BenefitInfo benefitsInfo;
    @Nullable
    private final Badge eventBadge;

    private EventPreviewInfo(int totalPriceBeforeDiscount, BenefitInfo benefitsInfo, Badge eventBadge) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.benefitsInfo = benefitsInfo;
        this.eventBadge = eventBadge;
    }

    public static EventPreviewInfo from(Order order, VisitDay visitDay) {
        int totalPriceBeforeDiscount = order.getTotalPrice();
        if (totalPriceBeforeDiscount < POSSIBLE_EVENT_PRICE) {
            return new EventPreviewInfo(totalPriceBeforeDiscount, BenefitInfo.empty(), null);
        }
        FreeGiftPolicy freeGift = FreeGiftPolicy.from(totalPriceBeforeDiscount);
        BenefitInfo benefitsInfo = BenefitInfo.from(order, visitDay, freeGift);
        Badge eventBadge = Badge.from(benefitsInfo.getTotalBenefitPrice()).orElse(null);
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
        if (eventBadge == null) {
            return "없음";
        }
        return eventBadge.name();
    }
}
