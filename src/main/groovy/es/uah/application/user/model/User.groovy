package es.uah.application.user.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * User domain class.
 */
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class User {

    /**
     * Code.
     */
    Long code

    /**
     * Username.
     */
    String username

    /**
     * Encrypted password.
     */
    String password

    /**
     * Enabled.
     */
    Boolean enabled

    /**
     * Attempt.
     */
    Integer attempt

    /**
     * Email.
     */
    String email

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
     * Date of birth.
     */
    Date birthDate

    /**
     * Registration date.
     */
    Date registrationDate

    /**
     * Termination date.
     */
    Date terminationDate

    /**
     * Role.
     */
    Role role
}
