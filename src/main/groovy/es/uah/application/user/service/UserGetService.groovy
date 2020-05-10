package es.uah.application.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserResponse
import es.uah.application.user.model.mapper.UserMapper
import groovy.util.logging.Slf4j

/**
 * Service to get users.
 */
@Service
@Slf4j
class UserGetService {

    @Autowired
    private UserDAO userDAO

    @Autowired
    private UserMapper userMapper

    /**
     * Method that returns all users
     * 
     * @return List with users
     */
    List<UserResponse> getAll() {

        List<User> users = userDAO.getAll()
        List<UserResponse> usersResponse = []

        users.each { user ->
            usersResponse << userMapper.map(user)
        }

        return usersResponse
    }
}
