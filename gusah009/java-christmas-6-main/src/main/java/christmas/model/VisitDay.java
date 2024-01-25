package christmas.model;

import christmas.exception.ChristmasException;

public class VisitDay {

    private final int day;

    private VisitDay(int day) {
        this.day = day;
    }

    public static VisitDay from(String inputDay) {
        int day = validateAndGetDay(inputDay);
        return new VisitDay(day);
    }

    private static int validateAndGetDay(String inputDay) {
        try {
            int day = Integer.parseInt(inputDay);
            if (day < 1 || day > 31) {
                throw new RuntimeException();
            }
            return day;
        } catch (Exception e) {
            throw new ChristmasException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return String.valueOf(this.day);
    }
}
