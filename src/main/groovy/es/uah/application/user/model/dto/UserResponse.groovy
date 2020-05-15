package es.uah.application.user.model.dto;

import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;

/**
 * Class containing the user response data.
 */
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class UserResponse {

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
     * Date of birth.
     */
    Date birthDate

    /**
     * Registration date.
     */
    Date registrationDate

    /**
     * Terminate date.
     */
    Date terminationDate
}
