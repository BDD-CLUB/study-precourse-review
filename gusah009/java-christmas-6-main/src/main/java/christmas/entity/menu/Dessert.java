package christmas.entity.menu;

import christmas.entity.price.Price;

public enum Dessert implements Menu {
    초코케이크(15_000),
    아이스크림(5_000),
    ;

    private final Price price;

    Dessert(int price) {
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
