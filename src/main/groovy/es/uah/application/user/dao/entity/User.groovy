package es.uah.application.user.dao.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * User entity definition.
 */
@Entity
@Table(name = 'USER')
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class User {

    @Id
    @GeneratedValue(generator = 'USER_SEQ', strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = 'USER_SEQ', sequenceName = 'USER_SEQ',
    allocationSize = 1)
    @Column(name = 'CODE', nullable = false)
    @NotNull
    Long code
}
