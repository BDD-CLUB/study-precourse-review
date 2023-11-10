package christmas.entity.menu;

public enum Drink implements Menu {
    제로콜라(3_000),
    레드와인(60_000),
    샴페인(25_000),
    ;

    private final int price;

    Drink(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getMenuName() {
        return this.name();
    }
}
