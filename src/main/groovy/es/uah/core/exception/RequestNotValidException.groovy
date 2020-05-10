package es.uah.core.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Request not valid exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class RequestNotValidException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    RequestNotValidException() {
        super()
    }

    /**
     * {@inheritDoc}
     */
    RequestNotValidException(String message) {
        super(message)
    }
}
