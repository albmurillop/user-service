package es.uah.application.user.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import es.uah.application.user.dao.entity.User

/**
 * User interface to the repository.
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {
}
