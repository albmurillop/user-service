package es.uah.application.user.service

import static es.uah.application.user.util.Constants.ENABLED
import static es.uah.application.user.util.Constants.ROLE_USER
import static es.uah.application.user.util.Constants.ZERO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import es.uah.application.user.dao.RoleDAO
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.feign.NotificationRest
import es.uah.application.user.handler.ExceptionHandler
import es.uah.application.user.model.Role
import es.uah.application.user.model.User
import es.uah.core.exception.EntityNotFoundException
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
    private RoleDAO roleDAO

    @Autowired
    private PasswordEncoder passwordEncoder

    @Autowired
    private NotificationRest notificationRest

    /**
     * Method of registering an user.
     * 
     * @param user Object with the data of the user to be registered
     * @return User
     */
    @Transactional
    User register(User user) {
        try {
            if (!user)
                throw new RequestNotValidException('The object with the new user\'s data is null')

            Role role = roleDAO.getByName(ROLE_USER)
            if (!role)
                throw new EntityNotFoundException("The role has not been found: ${ROLE_USER}")

            user.password = passwordEncoder.encode(user.password)
            user.registrationDate = new Date()
            user.enabled = ENABLED
            user.attempt = ZERO
            user.role = role
            User registeredUser = userDAO.save(user)

            ResponseEntity<?> responseNotificationService = notificationRest.notifyUserRegistration(registeredUser)

            return registeredUser
        } catch (Exception exception) {
            ExceptionHandler.manage(exception)
        }
    }
}