package es.uah.application.user.dao

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import es.uah.application.user.dao.entity.RoleEntity
import es.uah.application.user.dao.repository.RoleRepository
import es.uah.application.user.handler.ExceptionHandler
import es.uah.application.user.model.Role
import es.uah.core.exception.IllegalArgumentException
import groovy.util.logging.Slf4j

/**
 * Role data access object.
 */
@Service
@Slf4j
class RoleDAO {

    @Autowired
    private RoleRepository roleRepository

    @Autowired
    private DozerBeanMapper mapper

    /**
     * Method to get a role by name.
     * 
     * @param name Name of role
     * @return Role
     */
    Role getByName(String name) {

        if (!name)
            ExceptionHandler.manage(new IllegalArgumentException('The name is null'))

        Role role
        RoleEntity roleEntityDB = roleRepository.findByName(name)
        if (roleEntityDB)
            role = mapper.map(roleEntityDB, Role)

        return role
    }
}
