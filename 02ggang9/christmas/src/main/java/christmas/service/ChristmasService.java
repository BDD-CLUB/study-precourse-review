package christmas.service;

import christmas.domain.OrderSheet;
import christmas.domain.badge.Badge;
import christmas.domain.menu.Event;
import christmas.repository.ChristmasOrderMenuRepository;

import java.util.Arrays;

import static christmas.domain.badge.Badge.*;


public class ChristmasService {

    private final ChristmasOrderMenuRepository christmasOrderMenuRepository;

    public ChristmasService(ChristmasOrderMenuRepository christmasOrderMenuRepository) {
        this.christmasOrderMenuRepository = christmasOrderMenuRepository;
    }

    public Integer calculateTotalAmount() {
        OrderSheet findOrderSheet = christmasOrderMenuRepository.findOrderSheetById(0L);
        return findOrderSheet.calculateTotalPrice();
    }

    public OrderSheet findOrderSheet() {
        return christmasOrderMenuRepository.findOrderSheetById(0L);
    }

    public Badge findBadge(int totalBenefitPrice) {
        return Arrays.stream(values())
                .filter(badge -> badge.getFunction().apply(totalBenefitPrice))
                .findFirst()
                .orElse(NOTING);
    }
}
