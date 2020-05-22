package unit.es.uah.application.user.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import es.uah.application.user.dao.RoleDAO
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.feign.NotificationRest
import es.uah.application.user.model.Role
import es.uah.application.user.model.User
import es.uah.application.user.service.UserRegisterService
import es.uah.core.exception.EntityNotFoundException
import es.uah.core.exception.RequestNotValidException
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserRegisterService unit test class.
 */
class UserRegisterServiceUnitSpec extends Specification{

    @Subject
    private UserRegisterService userRegisterService

    private UserDAO userDAO = Mock(UserDAO)
    
    private RoleDAO roleDAO = Mock(RoleDAO)

    private PasswordEncoder passwordEncoder = Mock(PasswordEncoder)
    
    private NotificationRest notificationRest = Mock(NotificationRest)

    def setup() {
        userRegisterService = new UserRegisterService(
            userDAO: userDAO,
            roleDAO: roleDAO,
            passwordEncoder: passwordEncoder,
            notificationRest: notificationRest
        )
    }

    def 'Register user'() {
        given:
            User user = new User(password: 'password')
            String passwordEncode = 'passwordEncode'
            User registeredUser = new User(code: 1L, password: passwordEncode)
            Role role = new Role(code: 1L, name: 'ROLE_USER')
            
            ResponseEntity<?> responseNotificationService =
                new ResponseEntity(HttpStatus.CREATED)
            
        and:
            roleDAO.getByName(_ as String) >> role
            passwordEncoder.encode(_ as String) >> passwordEncode
            userDAO.save(_ as User) >> registeredUser
            notificationRest.notifyUserRegistration(_ as User) >> responseNotificationService                

        when:
            User result = userRegisterService.register(user)

        then:
            result.password == passwordEncode
    }
    
    def 'Register user, but ROLE_USER not found'() {
        given:
            User user = new User(password: 'password')
            Role role = null
            
        and:
            roleDAO.getByName(_ as String) >> role

        when:
            User result = userRegisterService.register(user)

        then:
            !result
            0 * passwordEncoder.encode(_ as String)
            0 * userDAO.save(_ as User)
            0 * notificationRest.notifyUserRegistration(_ as User)
            thrown(EntityNotFoundException)
    }

    def 'Register user, but user parameter is null'() {
        given:
            User user = null

        when:
            User result = userRegisterService.register(user)

        then:
            !result
            0 * roleDAO.getByName(_ as String)
            0 * passwordEncoder.encode(_ as String)
            0 * userDAO.save(_ as User)
            0 * notificationRest.notifyUserRegistration(_ as User)
            thrown(RequestNotValidException)
    }
    
    def 'Register user, but error in notification-service'() {
        given:
            User user = new User(password: 'password')
            String passwordEncode = 'passwordEncode'
            User registeredUser = new User(code: 1L, 
                registrationDate: new Date(), password: passwordEncode)
            Role role = new Role(code: 1L, name: 'ROLE_USER')
            
        and:
            roleDAO.getByName(_ as String) >> role
            passwordEncoder.encode(_ as String) >> passwordEncode
            userDAO.save(_ as User) >> registeredUser
            notificationRest.notifyUserRegistration(_ as User) >>
                { throw new Exception('Error notification-service') }

        when:
            User result = userRegisterService.register(user)
            
        then:
            !result
            thrown(Exception)
    }
}
