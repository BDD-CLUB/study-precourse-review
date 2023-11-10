package christmas.model.order;

import christmas.entity.menu.Drink;
import christmas.entity.menu.Menu;
import christmas.exception.ChristmasException;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private static final String MENU_SEPARATOR = ",";
    private static final String MENU_COUNT_SEPERATOR = "-";
    private static final int MAX_TOTAL_MENU_COUNT = 20;

    private final Map<Menu, MenuCount> menus;

    private Order(Map<Menu, MenuCount> menus) {
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

    private static class MenuCount {

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
    }
}
