package es.uah.application.user.service

import java.time.LocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserRequest
import es.uah.application.user.model.UserResponse
import es.uah.application.user.model.mapper.UserMapper
import es.uah.core.exception.RequestNotValidException
import groovy.util.logging.Slf4j

/**
 * Service to register users.
 */
@Service
@Slf4j
class UserRegisterService {

    @Autowired
    private UserDAO userDAO

    @Autowired
    private UserMapper userMapper
    
    @Autowired
    private PasswordEncoder passwordEncoder

    /**
     * Method of registering an user.
     * 
     * @param userRequest Object with the data of the user to be registered
     * @return UserResponse
     */
    @Transactional
    UserResponse register(UserRequest userRequest) {

        if (!userRequest)
            throw new RequestNotValidException('The object with the new user\'s data is null')

        userRequest.password = passwordEncoder.encode(userRequest.password)
        userRequest.registrationDate = LocalDateTime.now()

        User user = userMapper.map(userRequest)
        User registeredUser = userDAO.save(user)

        UserResponse userResponse = userMapper.map(registeredUser)

        // TODO Notification

        return userResponse
    }
}
