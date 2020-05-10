package es.uah.application.user.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import es.uah.application.user.model.UserRequest
import es.uah.application.user.model.UserResponse
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
@RequestMapping("/")
@Slf4j
class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService

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
    value = '/'
    )
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> register(
            @ApiParam(value = 'User data to be saved', required = true)
            @RequestBody UserRequest userRequest) {

        log.info "Register user: ${userRequest}"

        UserResponse registeredUser = userRegisterService.register(userRequest)

        log.info "Registered user: ${registeredUser}"

        return new ResponseEntity(registeredUser, HttpStatus.CREATED)
    }
}
