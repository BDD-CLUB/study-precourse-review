package christmas.entity.menu;

public enum Appetizer implements Menu {
    양송이수프(6_000),
    타파스(5_500),
    시저샐러드(8_000),
    ;

    private final int price;

    Appetizer(int price) {
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
