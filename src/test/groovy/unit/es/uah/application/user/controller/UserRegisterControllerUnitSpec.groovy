package unit.es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserRegisterController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserRegisterRequest
import es.uah.application.user.model.dto.UserResponse
import es.uah.application.user.service.UserRegisterService
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserRegisterController unit test class.
 */
class UserRegisterControllerUnitSpec extends Specification {

    @Subject
    private UserRegisterController userRegisterController
    
    private UserRegisterService userRegisterService = Mock(UserRegisterService)

    private DozerBeanMapper mapper = Mock(DozerBeanMapper)

    def setup() {
        userRegisterController = new UserRegisterController(
            userRegisterService: userRegisterService,
            mapper: mapper
        )
    }

    def 'Register user'() {
        given:
            UserRegisterRequest userRegisterRequest = new UserRegisterRequest(name: 'name')
            User userToRegister = new User(name: 'name')
            User registeredUser = new User(code: 1L, name: 'name')
            UserResponse registeredUserResponse = new UserResponse(name: 'name')

        and:
            mapper.map(_ as UserRegisterRequest, User) >> userToRegister
            userRegisterService.register(_ as User) >> registeredUser
            mapper.map(_ as User, UserResponse) >> registeredUserResponse

        when:
            ResponseEntity<?> result = userRegisterController.register(userRegisterRequest)

        then:
            result.statusCode == HttpStatus.CREATED
    }
}
