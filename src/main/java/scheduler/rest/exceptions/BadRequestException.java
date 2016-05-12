package scheduler.rest.exceptions;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by c113554 on 05/10/2016.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException() {

    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
