package es.uah.application.user.model.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Class containing the user request data.
 */
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class UserRequest {

    /**
     * Username.
     */
    String username

    /**
     * Password.
     */
    String password

    /**
     * Name.
     */
    String name

    /**
     * Email.
     */
    String email

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
}
