package scheduler.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Response;

/**
 * Created by c113554 on 05/10/2016.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{
    public ConflictException() {
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}
