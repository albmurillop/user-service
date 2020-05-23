package es.uah.application.user.util

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Constants.
 */
@EqualsAndHashCode
@ToString
class Constants {

    /**
     * Handling the exception.
     */
    public static final String MANAGE_EXCEPTION = 'Handling the exception'

    /**
     * Role user.
     */
    public static final String ROLE_USER = 'ROLE_USER'

    /**
     * Zero.
     */
    public static final Integer ZERO = 0

    /**
     * Enabled.
     */
    public static final Boolean ENABLED = true

    /**
     * Name of notification service.
     */
    public static final String NOTIFICATION_SERVICE = 'notification-service'

    /**
     * Path Post of notification-service.
     */
    public static final String NOTIFICATION_SERVICE_POST = '/notifications/user-register'
}