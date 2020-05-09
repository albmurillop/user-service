package es.uah.application.user.model.mapper

import org.springframework.stereotype.Component
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserResponse

/**
 * User mapper definition.
 */
@Component
class UserMapper {

    /**
     * Method in charge of mapping a user object
     * in userResponse.
     * 
     * @param user User
     * @return UserResponse
     */
    UserResponse map(User user) {

        UserResponse userResponse
        
        if (user) {
            userResponse = new UserResponse(
                code: user.code,
                name: user.name,
                firstSurname: user.firstSurname,
                secondSurname: user.secondSurname,
                email: user.email,
                birthDate: user.birthDate,
                startDate: user.startDate,
                endDate: user.endDate
            )
        }

        return userResponse
    }
}
