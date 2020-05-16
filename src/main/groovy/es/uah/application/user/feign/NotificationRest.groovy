package es.uah.application.user.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import es.uah.application.user.model.User

/**
 * Notification REST with Feign.
 */
@FeignClient(name = 'notification-service')
interface NotificationRest {

    /**
     * Notify user registration.
     * 
     * @param user Object with data of user
     * @return Response
     */
    @PostMapping
    public ResponseEntity<?> notifyUserRegistration(@RequestBody User user)
}