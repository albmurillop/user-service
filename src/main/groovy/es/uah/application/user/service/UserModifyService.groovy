package es.uah.application.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import es.uah.application.user.dao.UserDAO
import es.uah.application.user.handler.ExceptionHandler
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserPatchRequest
import es.uah.core.exception.EntityNotFoundException
import es.uah.core.exception.RequestNotValidException
import groovy.util.logging.Slf4j

/**
 * Service to modify users.
 */
@Service
@Slf4j
class UserModifyService {

    @Autowired
    private UserDAO userDAO

    /**
     * Method of patching an user.
     * 
     * @param code Code of user
     * @param userPatchRequest Object with the data to be changed
     * @return Modified user
     */
    @Transactional
    User patch(Long code, UserPatchRequest userPatchRequest) {
        if (!code || !userPatchRequest)
            ExceptionHandler.manage(new RequestNotValidException('Null parameters to patch the user'))

        User user = userDAO.getByCode(code)
        if (!user)
            ExceptionHandler.manage(new EntityNotFoundException("Not found user with code: ${code}"))

        user.attempt = userPatchRequest.attempt
        user.enabled = userPatchRequest.enabled

        return userDAO.save(user)
    }
}
