package christmas.domain;

import static christmas.utils.MenuCategory.*;
import static christmas.utils.ErrorMessage.INVALID_ORDER_ERROR;

import christmas.utils.ChristmasException;
import christmas.utils.MenuCategory;
import java.util.List;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MAIN),
    BARBEQUE_LIP("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),
    ZERO_COKE("제로콜라", 3_000, DRINK),
    RED_WINE("레드와인", 60_000, DRINK),
    CHAMPAGNE("샴페인", 25_000, DRINK);

    private final String food;
    private final int price;
    private final MenuCategory category;

    Menu(String food, int price, MenuCategory category) {
        this.food = food;
        this.price = price;
        this.category = category;
    }


    public static Menu findMenu(String food) throws ChristmasException {
        for (Menu menu : Menu.values()) {
            if (menu.getFoodName().equals(food)) {
                return menu;
            }
        }
        throw new ChristmasException(INVALID_ORDER_ERROR);
    }

    public static int countCategoryNumber(List<Menu> menus, MenuCategory menuCategory) {
        int count = 0;
        for (Menu menu: menus) {
            if(menu.category.equals(menuCategory)) count++;
        }
        return count;
    }
    public String getFoodName() {
        return food;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return category;
    }
}
