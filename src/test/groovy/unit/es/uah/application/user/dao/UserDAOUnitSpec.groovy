package unit.es.uah.application.user.dao

import org.dozer.DozerBeanMapper
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.dao.entity.UserEntity
import es.uah.application.user.dao.repository.UserRepository
import es.uah.application.user.model.User
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
    
    private DozerBeanMapper mapper = Mock(DozerBeanMapper)

    def setup() {
        userDAO = new UserDAO(
            userRepository: userRepository,
            mapper: mapper
        )
    }

    def 'Get all users'() {
        given:
            UserEntity userEntity = new UserEntity(code: 1L)
            List<UserEntity> users = []
            
        and:
            userRepository.findAll() >> users
        
        when:
            List<User> result = userDAO.getAll()

        then:
            users.size() * mapper.map(_ as UserEntity, User)
    }
    
    def 'Save user'() {
        given:
            User user = new User(name: 'name')
            UserEntity userEntity = new UserEntity(name: 'name')
            UserEntity savedUserEntity = new UserEntity(code: 1L, name: 'name')
            User savedUser = new User(code: 1L, name: 'name')
            
        and:
            mapper.map(_ as User, UserEntity) >> userEntity
            userRepository.save(_ as UserEntity) >> savedUserEntity
            mapper.map(_ as UserEntity, User) >> savedUser
            
        when:
            User result = userDAO.save(user)

        then:
            result == savedUser
    }

    def 'Save user, but it\'s null'() {
        given:
            User user = null

        when:
            User result = userDAO.save(user)

        then:
            0 * mapper.map(_ as User, UserEntity)
            0 * userRepository.save(_ as UserEntity)
            0 * mapper.map(_ as UserEntity, User)
            thrown(IllegalArgumentException)
    }
}
