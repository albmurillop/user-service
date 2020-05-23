package unit.es.uah.application.user.service

import es.uah.application.user.dao.UserDAO
import es.uah.application.user.model.User
import es.uah.application.user.service.UserSearchService
import es.uah.core.exception.RequestNotValidException
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserSearchService unit test class.
 */
class UserSearchServiceUnitSpec extends Specification{

    @Subject
    private UserSearchService userSearchService

    private UserDAO userDAO = Mock(UserDAO)

    def setup() {
        userSearchService = new UserSearchService(
            userDAO: userDAO,
        )
    }

    def 'Search by username'() {
        given:
            String username = 'username'
            User user = new User(username: username)

        and:
            userDAO.getByUsername(_ as String) >> user

        when:
            User result = userSearchService.searchByUsername(username)

        then:
            result.username == user.username
    }
    
    def 'Search by username, but username is null'() {
        given:
             String username = null

        when:
            User result = userSearchService.searchByUsername(username)

        then:
            !result
            0 * userDAO.getByUsername(_ as String)
            thrown(RequestNotValidException)
    }
}
