package es.uah.application.user.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import es.uah.application.user.dao.entity.RoleEntity

/**
 * Role interface to the repository.
 */
@Repository
interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Find a role by name.
     * 
     * @param name Name of role
     * @return Role
     */
    RoleEntity findByName(String name)
}
