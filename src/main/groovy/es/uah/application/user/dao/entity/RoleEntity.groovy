package es.uah.application.user.dao.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Role entity definition.
 */
@Entity
@Table(name = 'roles')
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class RoleEntity {

    /**
     * Code.
     */
    @Id
    @GeneratedValue(generator = 'roles_seq', strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = 'roles_seq', sequenceName = 'roles_seq',
        allocationSize = 1)
    @Column(name = 'code', nullable = false)
    Long code

    /**
     * Name.
     */
    @Column(name = 'name', unique = true, nullable = false)
    String name
}
