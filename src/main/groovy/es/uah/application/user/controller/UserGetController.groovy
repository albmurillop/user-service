package es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserResponse
import es.uah.application.user.service.UserGetService
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

/**
 * User get controller implementation.
 */
@Api(value = 'UserGetController', description = 'Api of get users')
@RestController
@RequestMapping("/")
@Slf4j
class UserGetController {

    @Autowired
    private UserGetService userGetService

    @Autowired
    private DozerBeanMapper mapper

    @ApiOperation(
        notes = 'Method that returns all users.',
        nickname = 'getAll',
        produces = MediaType.APPLICATION_JSON_VALUE,
        response = UserResponse,
        value = 'getAll'
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
    ResponseEntity<?> getAll() {

        log.info 'Get data from all users'

        List<User> users = userGetService.getAll()
        List<UserResponse> usersResponse = []
        users?.each {
            usersResponse << mapper.map(it, UserResponse)
        }
        
        log.info "Users: ${usersResponse}"

        return new ResponseEntity(usersResponse, HttpStatus.OK)
    }
}
