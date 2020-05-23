package unit.es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserModifyController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserPatchRequest
import es.uah.application.user.service.UserModifyService
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserModifyController unit test class.
 */
class UserModifyControllerUnitSpec extends Specification {

    @Subject
    private UserModifyController userModifyController
    
    private UserModifyService userModifyService = Mock(UserModifyService)

    private DozerBeanMapper mapper = Mock(DozerBeanMapper)

    def setup() {
        userModifyController = new UserModifyController(
            userModifyService: userModifyService,
            mapper: mapper
        )
    }

    def 'Patch user'() {
        given:
            Long code = 1L
            Integer attempt = 0
            Boolean enabled = true
            UserPatchRequest userPatchRequest = new UserPatchRequest(
                attempt: attempt, enabled: enabled)
            User user = new User(code: code, attempt: attempt, enabled: enabled)

        and:
            userModifyService.patch(_ as Long, _ as UserPatchRequest) >> user

        when:
            ResponseEntity<?> result = userModifyController.patch(code, userPatchRequest)

        then:
            result.statusCode == HttpStatus.OK
    }
}
