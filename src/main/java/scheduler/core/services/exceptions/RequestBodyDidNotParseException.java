package scheduler.core.services.exceptions;

/**
 * Created by C113554 on 05/20/2016.
 */
public class RequestBodyDidNotParseException extends RuntimeException {
    public RequestBodyDidNotParseException() {
    }

    public RequestBodyDidNotParseException(String message) {
        super(message);
    }

    public RequestBodyDidNotParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestBodyDidNotParseException(Throwable cause) {
        super(cause);
    }

    public RequestBodyDidNotParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
