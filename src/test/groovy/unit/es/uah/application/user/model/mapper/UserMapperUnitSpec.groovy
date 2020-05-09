package unit.es.uah.application.user.model.mapper

import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserResponse
import es.uah.application.user.model.mapper.UserMapper
import spock.lang.Specification
import spock.lang.Subject

/**
 * UserMapper unit test class.
 */
class UserMapperUnitSpec extends Specification {

    @Subject
    private UserMapper userMapper

    def setup() {
        userMapper = new UserMapper()
    }

    def 'Map User to UserResponse'() {
        given:
            User user = new User(
                code: 1L
            )

        when:
            UserResponse result = userMapper.map(user)

        then:
            result.code == user.code
    }
}
