package christmas.entity.menu;

public enum Dessert implements Menu {
    초코케이크(15_000),
    아이스크림(5_000),
    ;

    private final int price;

    Dessert(int price) {
        this.price = price;
    }
}
