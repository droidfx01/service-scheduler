package scheduler.core.services.exceptions;

/**
 * Created by c113554 on 05/11/2016.
 */
public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException() {
    }

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }

    public EmployeeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public EmployeeAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
