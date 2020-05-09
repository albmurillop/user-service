package es.uah.application.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserResponse
import es.uah.application.user.model.mapper.UserMapper
import groovy.util.logging.Slf4j

/**
 * Service for users.
 */
@Service
@Slf4j
class UserService {

    @Autowired
    private UserDAO userDAO

    @Autowired
    private UserMapper userMapper

    /**
     * Method of returning existing users.
     * 
     * @return List with users
     */
    List<UserResponse> getUsers() {

        List<User> users = userDAO.getUsers()
        List<UserResponse> usersResponse = []

        users.each { user ->
            usersResponse << userMapper.map(user)
        }

        return usersResponse
    }
}
