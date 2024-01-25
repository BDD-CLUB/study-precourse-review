package christmas.entity.menu;

import christmas.entity.price.Price;

public enum Appetizer implements Menu {
    양송이수프(6_000),
    타파스(5_500),
    시저샐러드(8_000),
    ;

    private final Price price;

    Appetizer(int price) {
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
