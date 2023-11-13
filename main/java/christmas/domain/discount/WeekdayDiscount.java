package christmas.domain.discount;

import christmas.domain.OrderSheet;
import christmas.domain.detail.BenefitDetail;
import christmas.domain.discountpolicy.DiscountPolicy;
import christmas.domain.menu.MenuType;

import java.util.Map;

public class WeekdayDiscount extends Discount {

    private static final String DISCOUNT_NAME = "평일 할인: -";
    private static final Integer BASE_DISCOUNT_AMOUNT = 2023;

    public WeekdayDiscount(DiscountPolicy... policies) {
        super(policies);
    }

    @Override
    public void calculateDiscountAndSaveDetail(BenefitDetail benefitDetail, OrderSheet orderSheet) {
        for (DiscountPolicy policy : policies) {
            if (policy.isSatisfiedBy(orderSheet)) {
                benefitDetail.saveEvent(DISCOUNT_NAME, discountPrice(orderSheet));
            }
        }
    }

    private int discountPrice(OrderSheet orderSheet) {
        return BASE_DISCOUNT_AMOUNT * orderSheet.getOrders()
                .entrySet()
                .stream()
                .filter(o -> o.getKey().getMenuType() == MenuType.DESSERT)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
