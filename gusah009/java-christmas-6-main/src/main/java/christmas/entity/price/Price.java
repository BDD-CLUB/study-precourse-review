package christmas.entity.price;

import java.text.NumberFormat;
import java.util.Objects;

public class Price {

    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance();
    private static final Price ZERO = new Price(0);

    private final int price;

    private Price(int price) {
        this.price = price;
    }

    public static Price zero() {
        return ZERO;
    }

    public static Price from(int price) {
        if (price < 0) {
            throw new RuntimeException();
        }
        return new Price(price);
    }

    public int get() {
        return price;
    }

    public Price minus(Price other) {
        return Price.from(this.price - other.price);
    }

    @Override
    public String toString() {
        return NUMBER_FORMAT.format(price) + "원";
    }

    public String toStringWithMinus() {
        return NUMBER_FORMAT.format(-price) + "원";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return price == price1.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
