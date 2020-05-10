package es.uah.core.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Entity not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class EntityNotFoundException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    EntityNotFoundException() {
        super()
    }

    /**
     * {@inheritDoc}
     */
    EntityNotFoundException(String message) {
        super(message)
    }
}
