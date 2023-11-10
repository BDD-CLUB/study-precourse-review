package christmas.entity.menu;

public enum Main implements Menu {
    티본스테이크(55_000),
    바비큐립(54_000),
    해산물파스타(35_000),
    크리스마스파스타(25_000),
    ;

    private final int price;

    Main(int price) {
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
