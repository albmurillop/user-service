package es.uah.application.user.model.mapper

import org.springframework.stereotype.Component
import es.uah.application.user.dao.entity.User
import es.uah.application.user.model.UserRequest
import es.uah.application.user.model.UserResponse

/**
 * User mapper definition.
 */
@Component
class UserMapper {

    /**
     * Method in charge of mapping an User in UserResponse.
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
                registrationDate: user.registrationDate,
                terminationDate: user.terminationDate
            )
        }

        return userResponse
    }

    /**
     * Method in charge of mapping an UserRequest in User.
     *
     * @param userRequest UserRequest
     * @return User
     */
    User map(UserRequest userRequest) {

        User user

        if (userRequest) {
            user = new User(
                name: userRequest.name,
                firstSurname: userRequest.firstSurname,
                secondSurname: userRequest.secondSurname,
                email: userRequest.email,
                password: userRequest.password,
                birthDate: userRequest.birthDate,
                registrationDate: userRequest.registrationDate,
                terminationDate: userRequest.terminationDate
            )
        }

        return user
    }
}
