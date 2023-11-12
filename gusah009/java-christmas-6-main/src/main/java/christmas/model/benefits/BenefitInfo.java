package christmas.model.benefits;

import christmas.entity.discount.BenefitPolicy;
import christmas.entity.discount.ChristmasDiscountPolicy;
import christmas.entity.discount.DiscountPolicy;
import christmas.entity.discount.FreeGiftPolicy;
import christmas.entity.discount.SpecialDiscountPolicy;
import christmas.entity.discount.WeekdayDiscountPolicy;
import christmas.entity.discount.WeekendDiscountPolicy;
import christmas.entity.menu.Dessert;
import christmas.entity.menu.Main;
import christmas.model.VisitDay;
import christmas.model.order.Order;
import org.assertj.core.data.MapEntry;
import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class BenefitInfo {

    private final Map<Class<? extends BenefitPolicy>, Integer> benefitInfos;
    private final int totalDiscountPrice;
    private final int totalBenefitPrice;
    private final FreeGiftPolicy freeGiftPolicy;

    private BenefitInfo(Map<Class<? extends BenefitPolicy>, Integer> benefitInfos, int totalDiscountPrice, int totalBenefitPrice, FreeGiftPolicy freeGiftPolicy) {
        this.benefitInfos = benefitInfos;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalBenefitPrice = totalBenefitPrice;
        this.freeGiftPolicy = freeGiftPolicy;
    }

    public static BenefitInfo empty() {
        return new BenefitInfo(Map.of(), 0, 0, null);
    }

    public static BenefitInfo from(Order order, VisitDay visitDay, @Nullable FreeGiftPolicy freeGift) {
        Map<Class<? extends BenefitPolicy>, Integer> discountInfo = getDiscountInfo(order, visitDay);
        int totalDiscountPrice = getTotalBenefitPrice(discountInfo);

        HashMap<Class<? extends BenefitPolicy>, Integer> benefitInfoMap = new HashMap<>(discountInfo);
        if (freeGift != null) {
            benefitInfoMap.put(freeGift.getClass(), freeGift.getTotalPrice());
        }
        return new BenefitInfo(benefitInfoMap, totalDiscountPrice, getTotalBenefitPrice(benefitInfoMap), freeGift);
    }

    private static Map<Class<? extends BenefitPolicy>, Integer> getDiscountInfo(Order order, VisitDay visitDay) {
        List<DiscountPolicy> discountInfo = List.of(
                ChristmasDiscountPolicy.from(visitDay),
                SpecialDiscountPolicy.from(visitDay),
                WeekdayDiscountPolicy.from(visitDay, order.getMenuCount(Dessert.class)),
                WeekendDiscountPolicy.from(visitDay, order.getMenuCount(Main.class))
        );
        return discountInfo.stream()
                .map(discountPolicy -> MapEntry.entry(discountPolicy.getClass(), discountPolicy.getDiscountPriceIfDiscountable()))
                .filter(entry -> entry.getValue().isPresent())
                .collect(toMap(MapEntry::getKey, entry -> entry.getValue().get()));

    }

    private static int getTotalBenefitPrice(Map<Class<? extends BenefitPolicy>, Integer> discountInfo) {
        return discountInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public int getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public String getBenefitInfo() {
        if (benefitInfos.isEmpty()) {
            return "없음";
        }
        return benefitInfos.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + NumberFormat.getInstance().format(-entry.getValue()) + "원")
                .collect(Collectors.joining("\n"));
    }

    public String getFreeGift() {
        if (freeGiftPolicy == null) {
            return "없음";
        }
        return freeGiftPolicy.getFreeGiftInfo();
    }
}
