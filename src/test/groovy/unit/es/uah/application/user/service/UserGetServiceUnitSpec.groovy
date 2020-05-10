package unit.es.uah.application.user.service

import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserResponse
import es.uah.application.user.model.mapper.UserMapper
import es.uah.application.user.service.UserGetService
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserGetService unit test class.
 */
class UserGetServiceUnitSpec extends Specification{

    @Subject
    private UserGetService userGetService

    private UserDAO userDAO = Mock(UserDAO)

    private UserMapper userMapper = Mock(UserMapper)

    def setup() {
        userGetService = new UserGetService(
            userDAO: userDAO,
            userMapper: userMapper
        )
    }

    def 'Get users'() {
        given:
            List<User> users = [
                new User(
                    code: 1L
                )
            ]

            UserResponse userResponse = new UserResponse(
                code: 1L
            )

        and:
            userDAO.getAll() >> users
            userMapper.map(_ as User) >> userResponse

        when:
            List<UserResponse> result = userGetService.getAll()

        then:
            result.size() == users.size()
            result.first().code == users.first().code
    }

    def 'Get users, but there\'s no data'() {
        given:
            List<User> users = []
            UserResponse userResponse = null

        and:
            userDAO.getAll() >> users
            userMapper.map(_ as User) >> userResponse

        when:
            List<UserResponse> result = userGetService.getAll()

        then:
            !result
    }
}
