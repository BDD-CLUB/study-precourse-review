package christmas.entity.menu;

public interface Menu {

    static Menu from(String menuName) {
        try {
            return Appetizer.valueOf(menuName);
        } catch (Exception ignore) {
        }
        try {
            return Dessert.valueOf(menuName);
        } catch (Exception ignore) {
        }
        try {
            return Drink.valueOf(menuName);
        } catch (Exception ignore) {
        }
        try {
            return Main.valueOf(menuName);
        } catch (Exception ignore) {
        }
        throw new IllegalArgumentException("존재하지 않는 메뉴입니다.");
    }

    int getPrice();

    String getMenuName();
}
