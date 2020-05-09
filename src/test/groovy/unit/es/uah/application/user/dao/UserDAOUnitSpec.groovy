package unit.es.uah.application.user.dao

import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.User
import es.uah.application.user.dao.repository.UserRepository
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

    def 'Get users'() {
        when:
            List<User> result = userDAO.getUsers()

        then:
            1 * userRepository.findAll()
    }
}
