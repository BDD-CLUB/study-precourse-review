package christmas.domain;

import static christmas.model.Menu.countCategoryNumber;
import static christmas.utils.MenuCategory.*;
import static christmas.utils.ErrorMessage.ONLY_ORDERS_DRINK_ERROR;
import static christmas.utils.ErrorMessage.ORDER_LIMIT_OVER_ERROR;

import christmas.model.Badge;
import christmas.model.Menu;
import christmas.utils.ChristmasException;
import christmas.utils.MenuCategory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Customer {
    private final DecemberDate date;
    private final HashMap<Menu, Integer> orderedMenu;
    private Badge badge;

    private final static int LIMIT_ORDER_NUMBER = 20;

    public Customer(DecemberDate date, HashMap<Menu, Integer> orderedMenu) {
        this.date = date;
        validate(orderedMenu);
        this.orderedMenu = orderedMenu;
    }

    private void validate(HashMap<Menu, Integer> orderedMenu) {
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
        for (Entry<Menu,Integer> menu: orderedMenu.entrySet()) {
            if (menu.getKey().getCategory() == category) {
                count += menu.getValue();
            }
        }
        return count;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        Map<Menu, Integer> orderedMenu = getOrderedMenu();
        for (Entry<Menu, Integer> menu : orderedMenu.entrySet()) {
            totalPrice += menu.getValue() * menu.getKey().getPrice();
        }
        return totalPrice;
    }

    public void assignBadge(int totalDiscount){
        for (Badge badge : Badge.values()) {
            String badgeName = badge.getBadge(totalDiscount);
            if (badgeName != null) this.badge = badge;
        }
    }

    public Map<Menu, Integer> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenu);
    }

    public DecemberDate getDate() {
        return date;
    }

    public String getBadgeName() {
        if (badge == null) return "없음";
        return badge.name();
    }
}
