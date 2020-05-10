package es.uah.core.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Illegal argument exception exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class IllegalArgumentException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    IllegalArgumentException() {
        super()
    }

    /**
     * {@inheritDoc}
     */
    IllegalArgumentException(String message) {
        super(message)
    }
}
