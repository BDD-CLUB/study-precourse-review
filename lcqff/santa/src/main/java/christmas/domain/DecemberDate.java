package christmas.domain;

import static christmas.utils.DecemberDay.*;
import static christmas.utils.ErrorMessage.OUT_OF_RANGE_DATE_ERROR;

import christmas.utils.ChristmasException;

public class DecemberDate {
    private int date;

    public DecemberDate(int date) {
        isValidRange(date);
        this.date = date;
    }

    private void isValidRange(int date) throws ChristmasException {
        if (date < 1 || date > 31) {
            throw new ChristmasException(OUT_OF_RANGE_DATE_ERROR);
        }
    }

    public int getDate() {
        return date;
    }

    public boolean isBeforeChristmas() {
        return date <= CHRISTMAS.getDate();
    }

    public boolean isWeekend() {
        int firstDayDate = date % 7;
        return firstDayDate == FRIDAY.getDate() || firstDayDate == SATURDAY.getDate();
    }
}
