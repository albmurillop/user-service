package unit.es.uah.application.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserController
import es.uah.application.user.model.UserResponse
import es.uah.application.user.service.UserService
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserController unit test class.
 */
class UserControllerUnitSpec extends Specification {

    @Subject
    private UserController userController

    private UserService userService = Mock(UserService)

    def setup() {
        userController = new UserController(
            userService: userService
        )
    }

    def 'Get users'() {
        given:
            List<UserResponse> usersResponse = [
                new UserResponse(
                    code: 1L
                )
            ]

        and:
            userService.getUsers() >> usersResponse

        when:
            ResponseEntity<?> result = userController.getUsers()

        then:
            result.statusCode == HttpStatus.OK
            result.body == usersResponse
    }

    def 'Get users, but there\'s no data'() {
        given:
            List<UserResponse> usersResponse = []

        and:
            userService.getUsers() >> usersResponse

        when:
            ResponseEntity<?> result = userController.getUsers()

        then:
            result.statusCode == HttpStatus.OK
            !result.body
    }
}
