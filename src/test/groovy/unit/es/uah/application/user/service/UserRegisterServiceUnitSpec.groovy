package unit.es.uah.application.user.service

import org.springframework.security.crypto.password.PasswordEncoder
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.model.User
import es.uah.application.user.service.UserRegisterService
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

    private PasswordEncoder passwordEncoder = Mock(PasswordEncoder)

    def setup() {
        userRegisterService = new UserRegisterService(
            userDAO: userDAO,
            passwordEncoder: passwordEncoder
        )
    }

    def 'Register user'() {
        given:
            User user = new User(password: 'password')
            String passwordEncode = 'passwordEncode'
            User registeredUser = new User(code: 1L, 
                registrationDate: new Date(), password: passwordEncode)
            
        and:
            passwordEncoder.encode(_ as String) >> passwordEncode
            userDAO.save(_ as User) >> registeredUser

        when:
            User result = userRegisterService.register(user)

        then:
            result.password == passwordEncode
            result.registrationDate == registeredUser.registrationDate
    }

    def 'Register user, but user parameter is null'() {
        given:
            User user = null

        when:
            User result = userRegisterService.register(user)

        then:
            !result
            0 * passwordEncoder.encode(_ as String)
            0 * userDAO.save(_ as User)
            thrown(RequestNotValidException)
    }
}
