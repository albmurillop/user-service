package es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserResponse
import es.uah.application.user.service.UserSearchService
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

@Api(value = 'UserSearchController', description = 'Api of user search')
@RestController
@RequestMapping("/searchs")
@Slf4j
class UserSearchController {
    
    @Autowired
    private UserSearchService userSearchService
    
    @Autowired
    private DozerBeanMapper mapper
    
    /**
     * Method that searches for an user by code.
     * 
     * @param code Code of user
     * @return User
     */
    @ApiOperation(
        notes = 'Method that searches for an user by code.',
        nickname = 'searchByCode',
        produces = MediaType.APPLICATION_JSON_VALUE,
        response = UserResponse,
        value = 'searchByCode'
    )
    @ApiResponses(value = [
        @ApiResponse(code = 200, message = 'Success', response = UserResponse),
        @ApiResponse(code = 400, message = 'Bad Request'),
        @ApiResponse(code = 401, message = 'Unauthorized'),
        @ApiResponse(code = 403, message = 'Forbidden'),
        @ApiResponse(code = 404, message = 'Not Found'),
        @ApiResponse(code = 500, message = 'Failure')
    ])
    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = '/code/{code}'
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> searchByCode(
        @ApiParam(value = 'Code of user', required = true)
        @PathVariable Long code) {
        
         log.info "Search user with the code ${code}"
         
         User user = userSearchService.searchByCode(code)
         UserResponse userResponse = mapper.map(user, UserResponse)
         
         log.info "User: ${userResponse}"
         
         return new ResponseEntity(userResponse, HttpStatus.OK)
    }
    
    /**
     * Method that searches for an user by username.
     * 
     * @param username Username
     * @return User
     */
    @ApiOperation(
        notes = 'Method that searches for an user by username.',
        nickname = 'searchByUsername',
        produces = MediaType.APPLICATION_JSON_VALUE,
        response = UserResponse,
        value = 'searchByUsername'
    )
    @ApiResponses(value = [
        @ApiResponse(code = 200, message = 'Success', response = UserResponse),
        @ApiResponse(code = 400, message = 'Bad Request'),
        @ApiResponse(code = 401, message = 'Unauthorized'),
        @ApiResponse(code = 403, message = 'Forbidden'),
        @ApiResponse(code = 404, message = 'Not Found'),
        @ApiResponse(code = 500, message = 'Failure')
    ])
    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = '/username/{username}'
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> searchByUsername(
        @ApiParam(value = 'Username', required = true)
        @PathVariable String username) {
        
        log.info "Search user with the username ${username}"
        
        User user = userSearchService.searchByUsername(username)
        UserResponse userResponse = mapper.map(user, UserResponse)
        
        log.info "User: ${userResponse}"
        
        return new ResponseEntity(userResponse, HttpStatus.OK)        
    }
}
