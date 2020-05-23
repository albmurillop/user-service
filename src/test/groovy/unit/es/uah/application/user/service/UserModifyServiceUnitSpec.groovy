package unit.es.uah.application.user.service

import org.springframework.security.crypto.password.PasswordEncoder
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.feign.NotificationRest
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserPatchRequest
import es.uah.application.user.service.UserModifyService
import es.uah.core.exception.EntityNotFoundException
import es.uah.core.exception.RequestNotValidException
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserModifyService unit test class.
 */
class UserModifyServiceUnitSpec extends Specification {

    @Subject
    private UserModifyService userModifyService

    private UserDAO userDAO = Mock(UserDAO)
    
    private PasswordEncoder passwordEncoder = Mock(PasswordEncoder)
    
    private NotificationRest notificationRest = Mock(NotificationRest)

    def setup() {
        userModifyService = new UserModifyService(
            userDAO: userDAO
        )
    }

    def 'Patch user'() {
        given:
            Long code = 1L
            Integer attempt = 0
            Boolean enabled = true
            UserPatchRequest userPatchRequest = new UserPatchRequest(
                attempt: attempt, enabled: enabled)
            User user = new User(code: code)
            User modifiedUser = new User(code: code, attempt: attempt,
                enabled: enabled)
            
        and:
            userDAO.getByCode(_ as Long) >> user
            userDAO.save(_ as User) >> modifiedUser

        when:
            User result = userModifyService.patch(code, userPatchRequest)

        then:
            result.attempt == attempt
            result.enabled == enabled
    }
    
    def 'Patch user, but parameter is null'() {
        given:
            Long code = 1L
            UserPatchRequest userPatchRequest = null

        when:
            User result = userModifyService.patch(code, userPatchRequest)

        then:
            !result
            0 * userDAO.getByCode(_ as Long)
            0 * userDAO.save(_ as User)
            thrown(RequestNotValidException)
    }
    
    def 'Patch user, but nof found user'() {
        given:
            Long code = 1L
            Integer attempt = 0
            Boolean enabled = true
            UserPatchRequest userPatchRequest = new UserPatchRequest(
                attempt: attempt, enabled: enabled)
            User user = null
            
        and:
            userDAO.getByCode(_ as Long) >> user

        when:
            User result = userModifyService.patch(code, userPatchRequest)
            
        then:
            !result
            0 * userDAO.save(_ as User)
            thrown(EntityNotFoundException)
    }
}
