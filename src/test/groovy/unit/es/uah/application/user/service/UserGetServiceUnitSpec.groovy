package unit.es.uah.application.user.service

import es.uah.application.user.dao.UserDAO
import es.uah.application.user.model.User
import es.uah.application.user.service.UserGetService
import es.uah.core.exception.RequestNotValidException
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserGetService unit test class.
 */
class UserGetServiceUnitSpec extends Specification{

    @Subject
    private UserGetService userGetService

    private UserDAO userDAO = Mock(UserDAO)

    def setup() {
        userGetService = new UserGetService(
            userDAO: userDAO,
        )
    }

    def 'Get users'() {
        given:
            User user = new User(code: 1L, name: 'name')
            List<User> users = [user]

        and:
            userDAO.getAll() >> users

        when:
            List<User> result = userGetService.getAll()

        then:
            result.first().code == user.code
    }

    def 'Get users, but there\'s no data'() {
        given:
            List<User> users = []

        and:
            userDAO.getAll() >> users

        when:
            List<User> result = userGetService.getAll()

        then:
            !result
    }
    
    def 'Get user by code'() {
        given:
            Long code = 1L
            User user = new User(code: code)

        and:
            userDAO.getByCode(_ as Long) >> user

        when:
            User result = userGetService.getByCode(code)

        then:
            result.code == user.code
    }
    
    def 'Search by code, but code is null'() {
        given:
            Long code = null

        when:
            User result = userGetService.getByCode(code)

        then:
            !result
            0 * userDAO.getByCode(_ as Long)
            thrown(RequestNotValidException)
    }
}
