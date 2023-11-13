package christmas.utils;

public class ChristmasException extends IllegalArgumentException{
    public ChristmasException(ErrorMessage message) {
        super(message.getMessage());
    }
}
