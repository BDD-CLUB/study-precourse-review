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
import christmas.entity.price.Price;
import christmas.model.VisitDay;
import christmas.model.order.Order;
import org.assertj.core.data.MapEntry;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class BenefitInfo {

    private final Map<Class<? extends BenefitPolicy>, Price> benefitInfos;
    private final Price totalDiscountPrice;
    private final Price totalBenefitPrice;
    private final FreeGiftPolicy freeGiftPolicy;

    private BenefitInfo(Map<Class<? extends BenefitPolicy>, Price> benefitInfos, Price totalDiscountPrice, Price totalBenefitPrice, FreeGiftPolicy freeGiftPolicy) {
        this.benefitInfos = benefitInfos;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalBenefitPrice = totalBenefitPrice;
        this.freeGiftPolicy = freeGiftPolicy;
    }

    public static BenefitInfo empty() {
        return new BenefitInfo(Map.of(), Price.zero(), Price.zero(), null);
    }

    public static BenefitInfo from(Order order, VisitDay visitDay, @Nullable FreeGiftPolicy freeGift) {
        Map<Class<? extends BenefitPolicy>, Price> discountInfo = getDiscountInfo(order, visitDay);
        int totalDiscountPrice = getTotalBenefitPrice(discountInfo);

        HashMap<Class<? extends BenefitPolicy>, Price> benefitInfoMap = new HashMap<>(discountInfo);
        if (freeGift != null) {
            benefitInfoMap.put(freeGift.getClass(), freeGift.getTotalPrice());
        }
        return new BenefitInfo(benefitInfoMap, Price.from(totalDiscountPrice), Price.from(getTotalBenefitPrice(benefitInfoMap)), freeGift);
    }

    private static Map<Class<? extends BenefitPolicy>, Price> getDiscountInfo(Order order, VisitDay visitDay) {
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

    private static int getTotalBenefitPrice(Map<Class<? extends BenefitPolicy>, Price> discountInfo) {
        return discountInfo.values().stream()
                .map(Price::get)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Price getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public Price getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public String getBenefitInfo() {
        if (benefitInfos.isEmpty()) {
            return "없음";
        }
        return benefitInfos.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

    public String getFreeGift() {
        if (freeGiftPolicy == null) {
            return "없음";
        }
        return freeGiftPolicy.getFreeGiftInfo();
    }
}
