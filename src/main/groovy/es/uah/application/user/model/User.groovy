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
    Date birthDate

    /**
     * Registration date.
     */
    Date registrationDate

    /**
     * Termination date.
     */
    Date terminationDate
}
