package scheduler.core.services.exceptions;

/**
 * Created by C113554 on 05/13/2016.
 */
public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
    }

    public ScheduleNotFoundException(String message) {
        super(message);
    }

    public ScheduleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScheduleNotFoundException(Throwable cause) {
        super(cause);
    }

    public ScheduleNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
