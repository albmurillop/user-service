package unit.es.uah.application.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserRegisterController
import es.uah.application.user.model.UserRequest
import es.uah.application.user.model.UserResponse
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

    def setup() {
        userRegisterController = new UserRegisterController(
                userRegisterService: userRegisterService
                )
    }

    def 'Register user'() {
        given:
            UserRequest userRequest = new UserRequest(
                name: 'name'
            )
    
            UserResponse registeredUser = new UserResponse(
                code: 1L,
                name: 'name'
            )

        and:
            userRegisterService.register(_ as UserRequest) >> registeredUser

        when:
            ResponseEntity<?> result = userRegisterController.register(userRequest)

        then:
            result.statusCode == HttpStatus.CREATED
            result.body == registeredUser
    }
}
