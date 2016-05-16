package scheduler.core.services.exceptions;

/**
 * Created by C113554 on 05/16/2016.
 */
public class ScheduleAlreadyExistsException extends RuntimeException{
    public ScheduleAlreadyExistsException() {
    }

    public ScheduleAlreadyExistsException(String message) {
        super(message);
    }

    public ScheduleAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScheduleAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ScheduleAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
