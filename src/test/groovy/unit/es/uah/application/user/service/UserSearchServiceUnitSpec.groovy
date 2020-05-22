package unit.es.uah.application.user.service

import es.uah.application.user.dao.UserDAO
import es.uah.application.user.model.User
import es.uah.application.user.service.UserSearchService
import es.uah.core.exception.EntityNotFoundException
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

    def 'Search by code'() {
        given:
            Long code = 1L
            User user = new User(code: code)

        and:
            userDAO.getByCode(_ as Long) >> user

        when:
            User result = userSearchService.searchByCode(code)

        then:
            result.code == user.code
    }
    
    def 'Search by code, but code is null'() {
        given:
            Long code = null

        when:
            User result = userSearchService.searchByCode(code)

        then:
            !result
            0 * userDAO.getByCode(_ as Long)
            thrown(RequestNotValidException)
    }

    def 'Search by code, but not found user'() {
        given:
            Long code = 1L
            User user = null

        and:
            userDAO.getByCode(_ as Long) >> user

        when:
            User result = userSearchService.searchByCode(code)

        then:
            !result
            thrown(EntityNotFoundException)
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

    def 'Search by username, but not found user'() {
        given:
            String username = 'username'
            User user = null

        and:
            userDAO.getByUsername(_ as String) >> user

        when:
            User result = userSearchService.searchByUsername(username)

        then:
            !result
            thrown(EntityNotFoundException)
    }
}
