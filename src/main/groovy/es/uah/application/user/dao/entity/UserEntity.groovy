package es.uah.application.user.dao.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * User entity definition.
 */
@Entity
@Table(name = 'users')
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class UserEntity {

    /**
     * Code.
     */
    @Id
    @GeneratedValue(generator = 'users_seq', strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = 'users_seq', sequenceName = 'users_seq',
        allocationSize = 1)
    @Column(name = 'code', nullable = false)
    Long code

    /**
     * Username.
     */
    @Column(name = 'username', nullable = false)
    String username

    /**
     * Encrypted password.
     */
    @Column(name = 'password', nullable = false)
    String password

    /**
     * Enabled.
     */
    @Column(name = 'enabled', nullable = false)
    Boolean enabled

    /**
     * Attempt.
     */
    @Column(name = 'attempt', nullable = false)
    Integer attempt

    /**
     * Email.
     */
    @Column(name = 'email', nullable = false)
    String email

    /**
     * Name.
     */
    @Column(name = 'name', nullable = false)
    String name

    /**
     * First surname.
     */
    @Column(name = 'first_surname', nullable = false)
    String firstSurname

    /**
     * Second surname.
     */
    @Column(name = 'second_surname', nullable = true)
    String secondSurname

    /**
     * Date of birth.
     */
    @Column(name = 'birth_date', nullable = false)
    @Temporal(value = TemporalType.DATE)
    Date birthDate

    /**
     * Registration date.
     */
    @Column(name = 'registration_date', nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    Date registrationDate

    /**
     * Termination date.
     */
    @Column(name = 'termination_date', nullable = true)
    @Temporal(value = TemporalType.TIMESTAMP)
    Date terminationDate

    /**
     * Role.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'role_code', referencedColumnName = 'code', nullable = false,
        insertable = true, updatable = false)
    RoleEntity role
}
