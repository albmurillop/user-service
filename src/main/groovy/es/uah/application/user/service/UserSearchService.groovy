package es.uah.application.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.handler.ExceptionHandler
import es.uah.application.user.model.User
import es.uah.core.exception.EntityNotFoundException
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
     * Method of searching for an user by code.
     * 
     * @param code Code of user
     * @return User
     */
    @Transactional(readOnly = true)
    User searchByCode(Long code) {
        if (!code)
            ExceptionHandler.manage(new RequestNotValidException('The code is null'))

        User user = userDAO.getByCode(code)
        if (!user)
            ExceptionHandler.manage(new EntityNotFoundException("No user found with the code: ${code}"))

        return user
    }

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

        User user = userDAO.getByUsername(username)
        if (!user)
            ExceptionHandler.manage(new EntityNotFoundException("No user found with the username: ${username}"))

        return user
    }
}
