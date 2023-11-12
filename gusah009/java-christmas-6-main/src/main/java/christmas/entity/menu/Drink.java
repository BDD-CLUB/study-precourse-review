package christmas.entity.menu;

import christmas.entity.price.Price;

public enum Drink implements Menu {
    제로콜라(3_000),
    레드와인(60_000),
    샴페인(25_000),
    ;

    private final Price price;

    Drink(int price) {
        this.price = Price.from(price);
    }

    @Override
    public Price getPrice() {
        return this.price;
    }

    @Override
    public String getMenuName() {
        return this.name();
    }
}
