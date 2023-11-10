package christmas.model.benefits;

import christmas.model.VisitDay;
import christmas.model.order.Order;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static christmas.entity.discount.DiscountPolicy.getDiscountInfo;
import static java.util.Collections.unmodifiableMap;

public class EventPreviewInfo {

    private static final int POSSIBLE_EVENT_PRICE = 10_000;

    private final int totalPriceBeforeDiscount;
    private final Optional<FreeGift> freeGift;
    private final Map<String, Integer> benefitsInfo;
    private final int totalDiscountPrice;
    private final int totalBenefitPrice;
    private final Optional<Badge> eventBadge;

    private EventPreviewInfo(int totalPriceBeforeDiscount, Optional<FreeGift> freeGift, Map<String, Integer> benefitsInfo,
                             int totalDiscountPrice, int totalBenefitPrice, Optional<Badge> eventBadge) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.freeGift = freeGift;
        this.benefitsInfo = benefitsInfo;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalBenefitPrice = totalBenefitPrice;
        this.eventBadge = eventBadge;
    }

    public static EventPreviewInfo from(Order order, VisitDay visitDay) {
        int totalPriceBeforeDiscount = order.getTotalPrice();
        if (totalPriceBeforeDiscount < POSSIBLE_EVENT_PRICE) {
            return new EventPreviewInfo(totalPriceBeforeDiscount, Optional.empty(),
                    Map.of(), 0, 0, Optional.empty());
        }
        Optional<FreeGift> freeGift = FreeGift.from(totalPriceBeforeDiscount);
        Map<String, Integer> discountInfo = getDiscountInfo(order, visitDay);
        Map<String, Integer> benefitsInfo = freeGift.map(gift -> getBenefitsInfo(discountInfo, gift))
                .orElse(new HashMap<>(discountInfo));
        Optional<Badge> eventBadge = Badge.from(getTotalPrice(benefitsInfo));
        return new EventPreviewInfo(totalPriceBeforeDiscount, freeGift,
                unmodifiableMap(benefitsInfo), getTotalPrice(discountInfo), getTotalPrice(benefitsInfo), eventBadge);
    }

    private static Map<String, Integer> getBenefitsInfo(Map<String, Integer> discountInfo, FreeGift freeGift) {
        Map<String, Integer> benefitsInfo = new HashMap<>(discountInfo);
        benefitsInfo.put(freeGift.toString(), freeGift.getTotalPrice());
        return benefitsInfo;
    }

    private static int getTotalPrice(Map<String, Integer> benefitsInfo) {
        return benefitsInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public String getTotalPriceBeforeDiscount() {
        return NumberFormat.getInstance().format(totalPriceBeforeDiscount);
    }

    public String getFreeGift() {
        return freeGift.map(FreeGift::getFreeGiftInfo).orElse("없음");
    }

    public String getBenefitInfo() {
        if (benefitsInfo.isEmpty()) {
            return "없음";
        }
        return benefitsInfo.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + NumberFormat.getInstance().format(-entry.getValue()) + "원")
                .collect(Collectors.joining("\n"));
    }

    public String getTotalBenefitPriceToString() {
        return NumberFormat.getInstance().format(-this.totalBenefitPrice) + "원";
    }

    public String getExpectedPrice() {
        return NumberFormat.getInstance().format(this.totalPriceBeforeDiscount - this.totalDiscountPrice) + "원";
    }

    public String getEventBadge() {
        return eventBadge.map(Badge::name).orElse("없음");
    }
}
