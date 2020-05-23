package unit.es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserGetController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserResponse
import es.uah.application.user.service.UserGetService
import es.uah.core.exception.EntityNotFoundException
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserGetController unit test class.
 */
class UserGetControllerUnitSpec extends Specification {

    @Subject
    private UserGetController userGetController

    private UserGetService userGetService = Mock(UserGetService)
    
    private DozerBeanMapper mapper = Mock(DozerBeanMapper)

    def setup() {
        userGetController = new UserGetController(
            userGetService: userGetService,
            mapper: mapper
        )
    }

    def 'Get all users'() {
        given:
            User user = new User(code: 1L, name: 'name')
            List<User> users = [user]
            UserResponse userResponse = new UserResponse(name: 'name')
            List<UserResponse> usersResponse = [userResponse]
            
        and:
            userGetService.getAll() >> users
            mapper.map(_ as User, UserResponse) >> userResponse

        when:
            ResponseEntity<?> result = userGetController.getAll()

        then:
            result.statusCode == HttpStatus.OK
            result.body == usersResponse
    }

    def 'Get all users, but there\'s no data'() {
        given:
            List<User> users = []

        and:
            userGetService.getAll() >> users

        when:
            ResponseEntity<?> result = userGetController.getAll()

        then:
            0 * mapper.map(_ as User, UserResponse)
            result.statusCode == HttpStatus.OK
            !result.body
    }
    
    def 'Get by code'() {
        given:
            Long code = 1L
            User user = new User(code: code, name: 'name')
            UserResponse userResponse = new UserResponse(name: 'name')
            
        and:
            userGetService.getByCode(_ as Long) >> user
            mapper.map(_ as User, UserResponse) >> userResponse

        when:
            ResponseEntity<?> result = userGetController.getByCode(code)

        then:
            result.statusCode == HttpStatus.OK
            result.body == userResponse
    }
    
    def 'Get by code, but not found'() {
        given:
            Long code = 1L
            User user = null
            
        and:
            userGetService.getByCode(_ as Long) >> user

        when:
            ResponseEntity<?> result = userGetController.getByCode(code)

        then:
            !result
            0 * mapper.map(_ as User, UserResponse)
            thrown(EntityNotFoundException)
    }
}
