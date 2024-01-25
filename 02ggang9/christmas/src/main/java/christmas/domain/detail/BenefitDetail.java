package christmas.domain.detail;

import christmas.domain.OrderSheet;
import christmas.domain.discount.*;
import christmas.domain.discountpolicy.*;
import christmas.domain.menu.Event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitDetail {

    private static final List<Discount> discounts = List.of(
            new ChristmasDiscount(new ChristmasDiscountPolicy()),
            new SpecialDiscount(new SpecialDiscountPolicy()),
            new WeekdayDiscount(new WeekdayDiscountPolicy()),
            new WeekendDiscount(new WeekendDiscountPolicy())
    );

    private final Map<String, Integer> details = new HashMap<>();
    private Event event = Event.NOTING;

    public BenefitDetail() {
    }

    public void saveEventDetails(OrderSheet orderSheet) {
        discounts.forEach(discount -> discount.calculateDiscountAndSaveDetail(this, orderSheet));
    }

    public void saveEvent(String eventName, int discountPrice) {
        details.put(eventName, discountPrice);
    }

    public Map<String, Integer> getDetails() {
        return details;
    }

    public Event getEvent() {
        return event;
    }

    public int getTotalBenefitPrice() {
        return event.getDiscountPrice() + calculateTotalDiscountPrice();
    }

    private int calculateTotalDiscountPrice() {
        return details.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalDiscountPrice() {
        return calculateTotalDiscountPrice();
    }

    public Event setEventGoods(int beforeDiscountPrice) {
        this.event = Arrays.stream(Event.values())
                .filter(event -> event.getFunction().apply(beforeDiscountPrice))
                .findFirst()
                .orElse(Event.NOTING);

        return this.event;
    }
}
