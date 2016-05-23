package scheduler.core.services.exceptions;

/**
 * Created by C113554 on 05/19/2016.
 */
public class DateDidNotParseException extends RuntimeException {
    public DateDidNotParseException() {
    }

    public DateDidNotParseException(String message) {
        super(message);
    }

    public DateDidNotParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateDidNotParseException(Throwable cause) {
        super(cause);
    }

    public DateDidNotParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
