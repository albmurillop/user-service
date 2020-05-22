package unit.es.uah.application.user.dao

import org.dozer.DozerBeanMapper
import es.uah.application.user.dao.RoleDAO
import es.uah.application.user.dao.entity.RoleEntity
import es.uah.application.user.dao.repository.RoleRepository
import es.uah.application.user.model.Role
import es.uah.core.exception.IllegalArgumentException
import spock.lang.Specification
import spock.lang.Subject

/**
 * RoleDAO unit test class.
 */
class RoleDAOUnitSpec extends Specification {

    @Subject
    private RoleDAO roleDAO

    private RoleRepository roleRepository = Mock(RoleRepository)
    
    private DozerBeanMapper mapper = Mock(DozerBeanMapper)

    def setup() {
        roleDAO = new RoleDAO(
            roleRepository: roleRepository,
            mapper: mapper
        )
    }

    def 'Get by name'() {
        given:
            String name = 'ROLE_USER'
            RoleEntity roleEntityDB = new RoleEntity(code: 1L, name: name)
            
        and:
           roleRepository.findByName(_ as String) >> roleEntityDB
        
        when:
            Role result = roleDAO.getByName(name)

        then:
            1 * mapper.map(_ as RoleEntity, Role)
    }
    
    def 'Get by name, but is null'() {
        given:
            String name = null
            
        when:
            Role result = roleDAO.getByName(name)

        then:
            !result
            0 * roleRepository.findByName(_ as String)
            0 * mapper.map(_ as RoleEntity, Role)
            thrown(IllegalArgumentException)
    }
}
