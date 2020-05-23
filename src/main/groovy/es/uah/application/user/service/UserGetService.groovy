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
 * Service to get users.
 */
@Service
@Slf4j
class UserGetService {

    @Autowired
    private UserDAO userDAO

    /**
     * Method that returns all users
     * 
     * @return List with users
     */
    @Transactional(readOnly = true)
    List<User> getAll() {
        return userDAO.getAll()
    }
    
    /**
     * Method of searching for an user by code.
     *
     * @param code Code of user
     * @return User
     */
    @Transactional(readOnly = true)
    User getByCode(Long code) {
        if (!code)
            ExceptionHandler.manage(new RequestNotValidException('The code is null'))

        return userDAO.getByCode(code)
    }
}
