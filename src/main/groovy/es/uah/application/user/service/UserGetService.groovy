package es.uah.application.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.model.User
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
}
