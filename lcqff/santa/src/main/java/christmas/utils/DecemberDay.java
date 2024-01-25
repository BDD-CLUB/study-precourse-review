package christmas.utils;

public enum DecemberDay {
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3),
    CHRISTMAS(25);

    private final int firstDayDate;

    DecemberDay(int firstDayDate) {
        this.firstDayDate = firstDayDate;
    }

    public int getDate() {
        return firstDayDate;
    };
}
