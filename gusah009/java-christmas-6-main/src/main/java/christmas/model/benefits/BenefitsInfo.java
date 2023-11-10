package christmas.model.benefits;

import christmas.model.order.Order;

import java.text.NumberFormat;
import java.util.Optional;

public class BenefitsInfo {

    private final Order order;
    private final int totalPriceBeforeDiscount;
    private final Optional<FreeGift> freeGift;

    private BenefitsInfo(Order order, int totalPriceBeforeDiscount, Optional<FreeGift> freeGift) {
        this.order = order;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.freeGift = freeGift;
    }

    public static BenefitsInfo from(Order order) {
        int totalPriceBeforeDiscount = order.getTotalPrice();
        return new BenefitsInfo(order, totalPriceBeforeDiscount, FreeGift.from(totalPriceBeforeDiscount));
    }

    public String getTotalPriceBeforeDiscount() {
        return NumberFormat.getInstance().format(totalPriceBeforeDiscount);
    }

    public String getFreeGift() {
        return freeGift.map(FreeGift::toString).orElse("없음");
    }
}
