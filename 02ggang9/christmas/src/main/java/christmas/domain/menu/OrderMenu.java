package christmas.domain.menu;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import static christmas.domain.menu.MenuType.*;

public enum OrderMenu {

    MUSHROOM_SOUP("양송이수프", APPETIZER, 6_000, (count) -> 6_000 * count),
    TAPAS("타파스", APPETIZER, 5_500, (count) -> 5_500 * count),
    CAESAR_SALAD("시저샐러드", APPETIZER, 8_000, (count) -> 8_000 * count),

    TBONE_STEAK("티본스테이크", MAIN, 55_000, (count) -> 55_000 * count),
    BARBECUE_RIBS("바비큐립", MAIN, 54_000, (count) -> 54_000 * count),
    SEAFOOD_PASTA("해산물파스타", MAIN, 35_000, (count) -> 35_000 * count),
    CHRISTMAS_PASTA("크리스마스파스타", MAIN, 25_000, (count) -> 25_000 * count),

    CHOCOLATE_CAKE("초코케이크", DESSERT, 15_000, (count) -> 15_000 * count),
    ICE_CREAM("아이스크림", DESSERT, 5_000, (count) -> 5_000 * count),

    ZERO_COKE("제로콜라", DRINK, 3_000, (count) -> 3_000 * count),
    RED_WINE("레드와인", DRINK, 60_000, (count) -> 60_000 * count),
    CHAMPAGNE("샴페인", DRINK, 25_000, (count) -> 25_000 * count),
    ;

    private final String MenuName;
    private final MenuType MenuType;
    private final Integer MenuPrice;
    private final Function<Integer, Integer> function;

    OrderMenu(String menuName, MenuType menuType, Integer menuPrice, Function<Integer, Integer> function) {
        MenuName = menuName;
        MenuType = menuType;
        MenuPrice = menuPrice;
        this.function = function;
    }

    public String getMenuName() {
        return MenuName;
    }

    public MenuType getMenuType() {
        return MenuType;
    }

    public Integer getMenuPrice() {
        return MenuPrice;
    }

    public Function<Integer, Integer> getFunction() {
        return function;
    }

    public Integer calculatePrice(int count) {
        return this.function.apply(count);
    }

    public static OrderMenu fromMenuName(String menuName) {
        return Arrays.stream(OrderMenu.values())
                .filter(orderMenu -> orderMenu.getMenuName().equals(menuName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
