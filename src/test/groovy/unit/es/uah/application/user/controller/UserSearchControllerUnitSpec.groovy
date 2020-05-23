package unit.es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserSearchController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserResponse
import es.uah.application.user.service.UserSearchService
import es.uah.core.exception.EntityNotFoundException
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserSearchController unit test class.
 */
class UserSearchControllerUnitSpec extends Specification {

    @Subject
    private UserSearchController userSearchController

    private UserSearchService userSearchService = Mock(UserSearchService)
    
    private DozerBeanMapper mapper = Mock(DozerBeanMapper)

    def setup() {
        userSearchController = new UserSearchController(
            userSearchService: userSearchService,
            mapper: mapper
        )
    }

    def 'Search by username'() {
        given:
            Long code = 1L
            String username = 'username'
            User user = new User(code: code, username: username)
            UserResponse userResponse = new UserResponse(username: username)
            
        and:
            userSearchService.searchByUsername(_ as String) >> user
            mapper.map(_ as User, UserResponse) >> userResponse

        when:
            ResponseEntity<?> result = userSearchController.searchByUsername(username)

        then:
            result.statusCode == HttpStatus.OK
            result.body == userResponse
    }
    
    def 'Search by username, but not found'() {
        given:
            String username = 'username'
            User user = null
            
        and:
            userSearchService.searchByUsername(_ as String) >> user

        when:
            ResponseEntity<?> result = userSearchController.searchByUsername(username)

        then:
            !result
            0 * mapper.map(_ as User, UserResponse)
            thrown(EntityNotFoundException)
    }
}
