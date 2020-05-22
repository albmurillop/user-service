package unit.es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import es.uah.application.user.controller.UserSearchController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserResponse
import es.uah.application.user.service.UserSearchService
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

    def 'Search by code'() {
        given:
            Long code = 1L
            User user = new User(code: code, name: 'name')
            UserResponse userResponse = new UserResponse(name: 'name')
            
        and:
            userSearchService.searchByCode(_ as Long) >> user
            mapper.map(_ as User, UserResponse) >> userResponse

        when:
            ResponseEntity<?> result = userSearchController.searchByCode(code)

        then:
            result.statusCode == HttpStatus.OK
            result.body == userResponse
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
}
