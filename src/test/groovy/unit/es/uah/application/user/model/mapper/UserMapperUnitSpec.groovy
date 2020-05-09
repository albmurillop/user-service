package unit.es.uah.application.user.model.mapper

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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
                code: 1L,
                name: 'name',
                firstSurname: 'firstSurname',
                secondSurname: 'secondSurname',
                email: 'email',
                password: 'password',
                birthDate: new LocalDate(1, 1, 1),
                startDate: new LocalDateTime(
                    new LocalDate(1, 1, 1),
                    new LocalTime(1, 1, 1, 1)
                )
            )

        when:
            UserResponse result = userMapper.map(user)

        then:
            result.code == user.code
            result.name == user.name
            result.firstSurname == user.firstSurname
            result.secondSurname == user.secondSurname
            result.email == user.email
            result.birthDate == user.birthDate
            result.startDate == user.startDate
            result.endDate == user.endDate
    }
}
