package christmas.exception;

public class ChristmasException extends RuntimeException {

    private static final String PREFIX = "[ERROR] ";

    public ChristmasException(String message) {
        super(PREFIX + message);
    }
}
