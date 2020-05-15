package es.uah.application.user.dao

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import es.uah.application.user.dao.entity.UserEntity
import es.uah.application.user.dao.repository.UserRepository
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
     * Method of saving an user
     * 
     * @param user User
     * @return User saved
     */
    User save(User user) {
        if (!user)
            throw new IllegalArgumentException('The user parameter is null')

        UserEntity userEntity = mapper.map(user, UserEntity)
        User savedUser = mapper.map(userRepository.save(userEntity), User)

        return savedUser
    }
}
