package es.uah.application.user.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import es.uah.application.user.model.UserResponse
import es.uah.application.user.service.UserService
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

/**
 * User controller implementation.
 */
@Api(value = 'UserController', description = 'The User Api')
@RestController
@RequestMapping("/")
@Slf4j
class UserController {

    @Autowired
    private UserService userService

    @ApiOperation(
    notes = 'Method that returns all users',
    nickname = 'getUsers',
    produces = MediaType.APPLICATION_JSON_VALUE,
    response = String,
    value = 'getUsers'
    )
    @ApiResponses(value = [
        @ApiResponse(code = 200, message = 'Success', response = UserResponse, responseContainer = 'List'),
        @ApiResponse(code = 400, message = 'Bad Request'),
        @ApiResponse(code = 401, message = 'Unauthorized'),
        @ApiResponse(code = 403, message = 'Forbidden'),
        @ApiResponse(code = 404, message = 'Not Found'),
        @ApiResponse(code = 500, message = 'Failure')
    ])
    @RequestMapping(
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE,
    value = '/'
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> getUsers() {

        log.info 'Get data from users'

        List<UserResponse> usersResponse = userService.getUsers()

        log.info "Users: ${usersResponse}"

        return new ResponseEntity(usersResponse, HttpStatus.OK)
    }
}
