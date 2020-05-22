package es.uah.application.user.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Role domain class.
 */
@EqualsAndHashCode
@ToString(includeNames = true, includeFields = true)
class Role {

    /**
     * Code.
     */
    Long code

    /**
     * Name.
     */
    String name
}
