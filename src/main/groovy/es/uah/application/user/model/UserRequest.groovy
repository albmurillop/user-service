package es.uah.application.user.model

import java.time.LocalDate
import java.time.LocalDateTime
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Class containing the user request data.
 */
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class UserRequest {

    /**
     * Name.
     */
    String name

    /**
     * First surname.
     */
    String firstSurname

    /**
     * Second surname.
     */
    String secondSurname

    /**
     * Email.
     */
    String email

    /**
     * Encrypted password.
     */
    String password

    /**
     * Date of birth.
     */
    LocalDate birthDate

    /**
     * Registration date.
     */
    LocalDateTime registrationDate

    /**
     * Termination date.
     */
    LocalDateTime terminationDate
}
