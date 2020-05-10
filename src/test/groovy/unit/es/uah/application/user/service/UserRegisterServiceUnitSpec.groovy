package unit.es.uah.application.user.service

import java.time.LocalDateTime
import org.springframework.security.crypto.password.PasswordEncoder
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserRequest
import es.uah.application.user.model.UserResponse
import es.uah.application.user.model.mapper.UserMapper
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

    private UserMapper userMapper = Mock(UserMapper)

    private PasswordEncoder passwordEncoder = Mock(PasswordEncoder)

    def setup() {
        userRegisterService = new UserRegisterService(
                userDAO: userDAO,
                userMapper: userMapper,
                passwordEncoder: passwordEncoder
                )
    }

    def 'Register user'() {
        given:
            UserRequest userRequest = new UserRequest(
                password: 'password'
            )

            String passwordEncode = 'passwordEncode'

            User user = new User(
                password: passwordEncode,
                registrationDate: LocalDateTime.now()
            )

            User registeredUser = new User(
                code: 1L,
                password: passwordEncode,
                registrationDate: LocalDateTime.now()
            )

            UserResponse userResponse = new UserResponse(
                code: 1L,
                registrationDate: LocalDateTime.now()
            )

        and:
            passwordEncoder.encode(_ as String) >> passwordEncode
            userMapper.map(_ as UserRequest) >> user
            userDAO.save(_ as User) >> registeredUser
            userMapper.map(_ as User) >> userResponse

        when:
            UserResponse result = userRegisterService.register(userRequest)

        then:
            result.code == userResponse.code
            result.registrationDate == userResponse.registrationDate
    }

    def 'Register user, but userRequest is null'() {
        given:
            UserRequest userRequest = null

        when:
            UserResponse result = userRegisterService.register(userRequest)

        then:
            !result
            0 * passwordEncoder.encode(_ as String)
            0 * userMapper.map(_ as UserRequest)
            0 * userDAO.save(_ as User)
            0 * userMapper.map(_ as User)
            thrown(RequestNotValidException)
    }
}
