package christmas.model.benefits;

import christmas.entity.discount.DiscountPolicy;
import christmas.model.VisitDay;
import christmas.model.order.Order;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableMap;

public class EventPreviewInfo {

    private static final int POSSIBLE_EVENT_PRICE = 10_000;

    private final Order order;
    private final int totalPriceBeforeDiscount;
    private final Optional<FreeGift> freeGift;
    private final Map<String, Integer> discountInfo;
    private final Map<String, Integer> benefitsInfo;
    private final int totalDiscountPrice;
    private final int totalBenefitPrice;

    private EventPreviewInfo(Order order, int totalPriceBeforeDiscount, Optional<FreeGift> freeGift, Map<String, Integer> discountInfo, Map<String, Integer> benefitsInfo, int totalDiscountPrice, int totalBenefitPrice) {
        this.order = order;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.freeGift = freeGift;
        this.discountInfo = discountInfo;
        this.benefitsInfo = benefitsInfo;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalBenefitPrice = totalBenefitPrice;
    }

    public static EventPreviewInfo from(Order order, VisitDay visitDay) {
        int totalPriceBeforeDiscount = order.getTotalPrice();
        if (totalPriceBeforeDiscount < POSSIBLE_EVENT_PRICE) {
            return new EventPreviewInfo(order, totalPriceBeforeDiscount, Optional.empty(),
                    Map.of(), Map.of(), 0, 0);
        }
        Optional<FreeGift> freeGift = FreeGift.from(totalPriceBeforeDiscount);
        Map<String, Integer> discountInfo = getDiscountInfo(order, visitDay);
        Map<String, Integer> benefitsInfo = freeGift.map(gift -> getBenefitsInfo(discountInfo, gift))
                .orElse(new HashMap<>(discountInfo));
        return new EventPreviewInfo(order, totalPriceBeforeDiscount, freeGift,
                unmodifiableMap(discountInfo), unmodifiableMap(benefitsInfo), getTotalPrice(discountInfo), getTotalPrice(benefitsInfo));
    }

    private static Map<String, Integer> getDiscountInfo(Order order, VisitDay visitDay) {
        Map<String, Integer> discountInfo = new HashMap<>();
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            discountPolicy.getDiscountPriceIfDiscountable(order, visitDay)
                    .ifPresent(discountPrice -> discountInfo.put(discountPolicy.toString(), discountPrice));
        }
        return discountInfo;
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
}
