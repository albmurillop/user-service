package es.uah.application.user.feign

import static es.uah.application.user.util.Constants.NOTIFICATION_SERVICE
import static es.uah.application.user.util.Constants.NOTIFICATION_SERVICE_POST
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import es.uah.application.user.model.User

/**
 * Notification REST with Feign.
 */
@FeignClient(name = NOTIFICATION_SERVICE)
interface NotificationRest {

    /**
     * Notify user registration.
     * 
     * @param user Object with data of user
     * @return Response
     */
    @PostMapping(value = NOTIFICATION_SERVICE_POST)
    public ResponseEntity<?> notifyUserRegistration(@RequestBody User user)
}