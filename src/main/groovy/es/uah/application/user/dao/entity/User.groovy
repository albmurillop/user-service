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

    /**
     * Name.
     */
    @Column(name = 'NAME', nullable = false)
    @NotNull
    String name

    /**
     * First surname.
     */
    @Column(name = 'FIRST_SURNAME', nullable = false)
    @NotNull
    String firstSurname

    /**
     * Second surname.
     */
    @Column(name = 'SECOND_SURNAME', nullable = true)
    String secondSurname

    /**
     * Email.
     */
    @Column(name = 'EMAIL', nullable = false)
    @NotNull
    String email

    /**
     * Encrypted password.
     */
    @Column(name = 'PASSWORD', nullable = false)
    @NotNull
    String password

    /**
     * Date of birth.
     */
    @Column(name = 'BIRTH_DATE', nullable = false)
    @NotNull
    LocalDate birthDate

    /**
     * Start date.
     */
    @Column(name = 'START_DATE', nullable = false)
    @NotNull
    LocalDateTime startDate

    /**
     * End date.
     */
    @Column(name = 'END_DATE', nullable = true)
    LocalDateTime endDate
}
