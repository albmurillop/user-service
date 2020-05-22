package es.uah.application.user.dao

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import es.uah.application.user.dao.entity.UserEntity
import es.uah.application.user.dao.repository.UserRepository
import es.uah.application.user.handler.ExceptionHandler
import es.uah.application.user.model.User
import es.uah.core.exception.IllegalArgumentException
import groovy.util.logging.Slf4j

/**
 * User data access object.
 */
@Service
@Slf4j
class UserDAO {

    @Autowired
    private UserRepository userRepository

    @Autowired
    private DozerBeanMapper mapper

    /**
     * Method to get all the users.
     * 
     * @return List with users
     */
    List<User> getAll() {
        List<User> users = []
        userRepository.findAll()?.each {
            users << mapper.map(it, User)
        }

        return users
    }

    /**
     * Method to get an user by code.
     * 
     * @param code Code of user
     * @return User
     */
    User getByCode(Long code) {
        if (!code)
            ExceptionHandler.manage(new IllegalArgumentException('The code is null'))

        User user
        UserEntity userEntityDB = userRepository.findByCode(code)
        if (userEntityDB)
            user = mapper.map(userEntityDB, User)

        return user
    }
    
    /**
     * Method to get an user by username.
     *
     * @param username Username
     * @return User
     */
    User getByUsername(String username) {
        if (!username)
            ExceptionHandler.manage(new IllegalArgumentException('The username is null'))

        User user
        UserEntity userEntityDB = userRepository.findByUsername(username)
        if (userEntityDB)
            user = mapper.map(userEntityDB, User)

        return user
    }

    /**
     * Method of saving an user
     * 
     * @param user User
     * @return User saved
     */
    User save(User user) {
        if (!user)
            ExceptionHandler.manage(new IllegalArgumentException('The user parameter is null'))

        UserEntity userEntity = mapper.map(user, UserEntity)
        User savedUser = mapper.map(userRepository.save(userEntity), User)

        return savedUser
    }
}
