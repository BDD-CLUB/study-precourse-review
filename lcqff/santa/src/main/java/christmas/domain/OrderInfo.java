package christmas.domain;

import static christmas.domain.Menu.countCategoryNumber;
import static christmas.utils.ErrorMessage.ONLY_ORDERS_DRINK_ERROR;
import static christmas.utils.ErrorMessage.ORDER_LIMIT_OVER_ERROR;
import static christmas.utils.MenuCategory.APPETIZER;
import static christmas.utils.MenuCategory.DESSERT;
import static christmas.utils.MenuCategory.MAIN;

import christmas.utils.ChristmasException;
import christmas.utils.MenuCategory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OrderInfo {
    private static final String ORDERED_MENU = "%s %d개";
    private final DecemberDate date;
    private final HashMap<Menu, Integer> orderedMenu;
    private int totalPrice;

    private final static int LIMIT_ORDER_NUMBER = 20;

    public OrderInfo(DecemberDate decemberDate, HashMap<Menu, Integer> orderedMenu) {
        this.date = decemberDate;
        this.orderedMenu = orderedMenu;
        validate();
        calculateTotalPrice();
    }

    private void validate() {
        OrderedOnlyDrinks(orderedMenu.keySet().stream().toList());
        OrderedOverLimit(orderedMenu.values().stream().toList());
    }

    private void OrderedOnlyDrinks(List<Menu> menu) {
        int notDrinkCount = countCategoryNumber(menu, APPETIZER) + countCategoryNumber(menu, MAIN) + countCategoryNumber(menu, DESSERT);
        if (notDrinkCount == 0) {
            throw new ChristmasException(ONLY_ORDERS_DRINK_ERROR);
        }
    }

    private void OrderedOverLimit(List<Integer> values) {
        int count = values.stream().reduce(0, Integer::sum);
        if (count > LIMIT_ORDER_NUMBER) {
            throw new ChristmasException(ORDER_LIMIT_OVER_ERROR);
        }
    }

    public int countCategory(MenuCategory category) {
        int count = 0;
        for (Entry<Menu, Integer> menu : orderedMenu.entrySet()) {
            if (menu.getKey().getCategory() == category) {
                count += menu.getValue();
            }
        }
        return count;
    }

    private void calculateTotalPrice() {
        Map<Menu, Integer> orderedMenu = getOrderedMenu();
        for (Entry<Menu, Integer> menu : orderedMenu.entrySet()) {
            totalPrice += menu.getValue() * menu.getKey().getPrice();
        }
    }

    public Map<Menu, Integer> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenu);
    }

    public DecemberDate getDate() {
        return date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return orderedMenu.entrySet().stream().
                map(menu -> ORDERED_MENU.formatted(menu.getKey().getFoodName(), menu.getValue()) + "\n").
                collect(Collectors.joining());
    }
}
