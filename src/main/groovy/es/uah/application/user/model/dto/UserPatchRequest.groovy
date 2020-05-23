package es.uah.application.user.model.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Class containing the user patch request data.
 */
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class UserPatchRequest {

    /**
     * Enabled.
     */
    Boolean enabled

    /**
     * Attempt.
     */
    Integer attempt
}
