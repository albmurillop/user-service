package es.uah.application.user.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import es.uah.application.user.dao.entity.User
import es.uah.application.user.dao.repository.UserRepository
import groovy.util.logging.Slf4j

/**
 * User data access object.
 */
@Service
@Slf4j
class UserDAO {
    
    @Autowired
    private UserRepository userRepository
    
    /**
     * Method to get all the users.
     * 
     * @return List with users
     */
    List<User> getUsers() {
        return userRepository.findAll()
    }
}
