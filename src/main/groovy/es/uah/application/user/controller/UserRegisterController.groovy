package es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserRegisterRequest
import es.uah.application.user.model.dto.UserResponse
import es.uah.application.user.service.UserRegisterService
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

/**
 * User register controller implementation.
 */
@Api(value = 'UserRegisterController', description = 'Api for user registration')
@RestController
@RequestMapping("/users")
@Slf4j
class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService

    @Autowired
    private DozerBeanMapper mapper

    /**
     * Method of registering an user.
     *
     * @param userRequest Object with the data of the new user
     */
    @ApiOperation(
        notes = 'Method of registering an user.',
        nickname = 'createUser',
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = 'createUser'
    )
    @ApiResponses(value = [
        @ApiResponse(code = 201, message = 'Created'),
        @ApiResponse(code = 400, message = 'Bad Request'),
        @ApiResponse(code = 500, message = 'Failure')
    ])
    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = ''
    )
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> register(
            @ApiParam(value = 'User data to be saved', required = true)
            @RequestBody UserRegisterRequest userRegisterRequest) {

        log.info "Register user: ${userRegisterRequest}"

        User userToRegister = mapper.map(userRegisterRequest, User)
        User registeredUser = userRegisterService.register(userToRegister)
        UserResponse registeredUserResponse = mapper.map(registeredUser, UserResponse)

        log.info "Registered user: ${registeredUserResponse}"

        return new ResponseEntity(HttpStatus.CREATED)
    }
}
