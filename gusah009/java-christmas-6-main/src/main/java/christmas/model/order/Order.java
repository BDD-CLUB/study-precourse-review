package christmas.model.order;

import christmas.entity.menu.Drink;
import christmas.entity.menu.Menu;
import christmas.entity.price.Price;
import christmas.exception.ChristmasException;
import org.assertj.core.util.VisibleForTesting;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {

    private static final String MENU_SEPARATOR = ",";
    private static final String MENU_COUNT_SEPERATOR = "-";
    private static final int MAX_TOTAL_MENU_COUNT = 20;

    private final Map<Menu, MenuCount> menus;

    @VisibleForTesting
    Order(Map<Menu, MenuCount> menus) {
        this.menus = menus;
    }

    public static Order from(String inputMenus) {
        Map<Menu, MenuCount> menus = validateAndGetMenus(inputMenus);
        return new Order(menus);
    }

    private static Map<Menu, MenuCount> validateAndGetMenus(String inputMenus) {
        try {
            Map<Menu, MenuCount> orderMenus = getOrderMenus(inputMenus);
            checkHasOnlyDrinkMenu(orderMenus);
            checkTotalMenuCount(orderMenus);
            return orderMenus;
        } catch (Exception e) {
            throw new ChristmasException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static Map<Menu, MenuCount> getOrderMenus(String inputMenus) {
        Map<Menu, MenuCount> orderMenus = new HashMap<>();
        String[] inputMenusAndCount = inputMenus.split(MENU_SEPARATOR);
        for (String inputMenuAndCount : inputMenusAndCount) {
            String[] menuAndCount = getMenuAndCount(inputMenuAndCount);
            Menu menu = Menu.from(menuAndCount[0]);
            MenuCount count = MenuCount.from(menuAndCount[1]);
            checkHasDuplicateMenu(menu, orderMenus);
            orderMenus.put(menu, count);
        }
        return orderMenus;
    }

    private static void checkHasDuplicateMenu(Menu menu, Map<Menu, MenuCount> orderMenus) {
        if (orderMenus.containsKey(menu)) {
            throw new RuntimeException();
        }
    }

    private static String[] getMenuAndCount(String inputMenuAndCount) {
        String[] menuAndCount = inputMenuAndCount.split(MENU_COUNT_SEPERATOR);
        if (menuAndCount.length != 2) {
            throw new RuntimeException();
        }
        return menuAndCount;
    }

    private static void checkHasOnlyDrinkMenu(Map<Menu, MenuCount> orderMenus) {
        if (orderMenus.keySet().stream().allMatch(menu -> menu instanceof Drink)) {
            throw new RuntimeException();
        }
    }

    private static void checkTotalMenuCount(Map<Menu, MenuCount> orderMenus) {
        int totalMenuCount = orderMenus.values().stream()
                .mapToInt(menuCount -> menuCount.count)
                .sum();
        if (totalMenuCount < 1 || totalMenuCount > MAX_TOTAL_MENU_COUNT) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return menus.entrySet().stream()
                .map(menuAndCount -> menuAndCount.getKey() + " " + menuAndCount.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }

    public Price getTotalPrice() {
        int totalPrice = menus.entrySet().stream()
                .mapToInt(menuAndCount -> menuAndCount.getKey().getPrice().get() * menuAndCount.getValue().count)
                .sum();
        return Price.from(totalPrice);
    }

    public int getMenuCount(Class<? extends Menu> menuType) {
        return menus.entrySet().stream()
                .filter(menuAndCount -> menuType.isInstance(menuAndCount.getKey()))
                .mapToInt(menuAndCount -> menuAndCount.getValue().count)
                .sum();
    }

    static class MenuCount {

        private final int count;

        private MenuCount(int count) {
            this.count = count;
        }

        public static MenuCount from(String inputCount) {
            int count = Integer.parseInt(inputCount);
            if (count < 1) {
                throw new RuntimeException();
            }
            return new MenuCount(count);
        }

        @Override
        public String toString() {
            return String.valueOf(this.count);
        }
    }
}
