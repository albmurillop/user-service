package es.uah.application.user.dao.entity

import java.time.LocalDate
import java.time.LocalDateTime
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
@Table(name = 'users')
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class User {

    @Id
    @GeneratedValue(generator = 'users_seq', strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = 'users_seq', sequenceName = 'users_seq',
    allocationSize = 1)
    @Column(name = 'code', nullable = false)
    @NotNull
    Long code

    /**
     * Name.
     */
    @Column(name = 'name', nullable = false)
    @NotNull
    String name

    /**
     * First surname.
     */
    @Column(name = 'first_surname', nullable = false)
    @NotNull
    String firstSurname

    /**
     * Second surname.
     */
    @Column(name = 'second_surname', nullable = true)
    String secondSurname

    /**
     * Email.
     */
    @Column(name = 'email', nullable = false)
    @NotNull
    String email

    /**
     * Encrypted password.
     */
    @Column(name = 'password', nullable = false)
    @NotNull
    String password

    /**
     * Date of birth.
     */
    @Column(name = 'birth_date', nullable = false)
    @NotNull
    LocalDate birthDate

    /**
     * Registration date.
     */
    @Column(name = 'registration_date', nullable = false)
    @NotNull
    LocalDateTime registrationDate

    /**
     * Termination date.
     */
    @Column(name = 'termination_date', nullable = true)
    LocalDateTime terminationDate
}
