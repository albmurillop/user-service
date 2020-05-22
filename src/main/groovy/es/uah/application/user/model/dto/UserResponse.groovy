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
     * Username.
     */
    String username

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
     * Terminate date.
     */
    Date terminationDate

    /**
     * Role.
     */
    RoleResponse role
}
