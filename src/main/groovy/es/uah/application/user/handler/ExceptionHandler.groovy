package es.uah.application.user.handler

import static es.uah.application.user.util.Constants.MANAGE_EXCEPTION
import org.springframework.stereotype.Service
import groovy.util.logging.Slf4j

/**
 * Exception handling service.
 */
@Service
@Slf4j
class ExceptionHandler {

    /**
     * Method of handling exceptions.
     * 
     * @param exception Exception
     */
    static void manage(Exception exception) {
        log.info "${MANAGE_EXCEPTION}"
        log.error "${exception.message}"
        throw exception
    }
}