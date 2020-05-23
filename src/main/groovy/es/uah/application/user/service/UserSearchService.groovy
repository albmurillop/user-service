package es.uah.application.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.handler.ExceptionHandler
import es.uah.application.user.model.User
import es.uah.core.exception.RequestNotValidException
import groovy.util.logging.Slf4j

/**
 * Service to search users.
 */
@Service
@Slf4j
class UserSearchService {

    @Autowired
    private UserDAO userDAO

    /**
     * Method of searching for an user by username.
     *
     * @param username Username
     * @return User
     */
    @Transactional(readOnly = true)
    User searchByUsername(String username) {
        if (!username)
            ExceptionHandler.manage(new RequestNotValidException('The username is null'))

        return userDAO.getByUsername(username)
    }
}
