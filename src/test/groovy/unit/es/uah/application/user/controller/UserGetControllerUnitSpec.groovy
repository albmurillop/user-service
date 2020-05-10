package unit.es.uah.application.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserGetController
import es.uah.application.user.model.UserResponse
import es.uah.application.user.service.UserGetService
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserGetController unit test class.
 */
class UserGetControllerUnitSpec extends Specification {

    @Subject
    private UserGetController userGetController

    private UserGetService userGetService = Mock(UserGetService)

    def setup() {
        userGetController = new UserGetController(
            userGetService: userGetService
        )
    }

    def 'Get all users'() {
        given:
            List<UserResponse> usersResponse = [
                new UserResponse(
                    code: 1L
                )
            ]

        and:
            userGetService.getAll() >> usersResponse

        when:
            ResponseEntity<?> result = userGetController.getAll()

        then:
            result.statusCode == HttpStatus.OK
            result.body == usersResponse
    }

    def 'Get all users, but there\'s no data'() {
        given:
            List<UserResponse> usersResponse = []

        and:
            userGetService.getAll() >> usersResponse

        when:
            ResponseEntity<?> result = userGetController.getAll()

        then:
            result.statusCode == HttpStatus.OK
            !result.body
    }
}
