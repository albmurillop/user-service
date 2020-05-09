package es.uah.application.user.model;

import java.time.LocalDate
import java.time.LocalDateTime
import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;

/**
 * Class containing the user response data.
 */
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class UserResponse {

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
     * Date of birth.
     */
    LocalDate birthDate

    /**
     * Start date.
     */
    LocalDateTime startDate

    /**
     * End date.
     */
    LocalDateTime endDate
}
