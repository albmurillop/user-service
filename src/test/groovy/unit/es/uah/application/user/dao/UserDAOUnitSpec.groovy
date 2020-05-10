package unit.es.uah.application.user.dao

import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.User
import es.uah.application.user.dao.repository.UserRepository
import es.uah.core.exception.IllegalArgumentException
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserDAO unit test class.
 */
class UserDAOUnitSpec extends Specification {

    @Subject
    private UserDAO userDAO

    private UserRepository userRepository = Mock(UserRepository)

    def setup() {
        userDAO = new UserDAO(
            userRepository: userRepository
        )
    }

    def 'Get all users'() {
        when:
            List<User> result = userDAO.getAll()

        then:
            1 * userRepository.findAll()
    }
    
    def 'Save user'() {
        given:
            User user = new User(
                code: 1L
            )

        when:
            User result = userDAO.save(user)

        then:
            1 * userRepository.save(_ as User)
    }

    def 'Save user, but it\'s null'() {
        given:
            User user = null

        when:
            User result = userDAO.save(user)

        then:
            0 * userRepository.save(_ as User)
            thrown(IllegalArgumentException)
    }
}
