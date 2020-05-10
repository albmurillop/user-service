package unit.es.uah.application.user.model.mapper

import java.time.LocalDate
import java.time.LocalDateTime
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserRequest
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
                birthDate: LocalDate.now(),
                registrationDate: LocalDateTime.now()
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
            result.registrationDate == user.registrationDate
            result.terminationDate == user.terminationDate
    }

    def 'Map UserRequest to User'() {
        given:
            UserRequest userRequest = new UserRequest(
                name: 'name',
                firstSurname: 'firstSurname',
                secondSurname: 'secondSurname',
                email: 'email',
                password: 'password',
                birthDate: LocalDate.now(),
                registrationDate: LocalDateTime.now()
            )

        when:
            User result = userMapper.map(userRequest)

        then:
            result.name == userRequest.name
            result.firstSurname == userRequest.firstSurname
            result.secondSurname == userRequest.secondSurname
            result.email == userRequest.email
            result.password == userRequest.password
            result.birthDate == userRequest.birthDate
            result.registrationDate == userRequest.registrationDate
            result.terminationDate == userRequest.terminationDate
    }
}
