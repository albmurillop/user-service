package es.uah.application.user.controller

import org.dozer.DozerBeanMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import es.uah.application.user.model.User
import es.uah.application.user.model.dto.UserPatchRequest
import es.uah.application.user.service.UserModifyService
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

/**
 * User modify controller implementation.
 */
@Api(value = 'UserModifyController', description = 'Api to modify users')
@RestController
@RequestMapping("/users")
@Slf4j
class UserModifyController {

    @Autowired
    private UserModifyService userModifyService

    @Autowired
    private DozerBeanMapper mapper

    /**
     * Method of patching an user.
     * 
     * @param code Code of user
     * @param userPatchRequest Object with the data to be changed
     * @return ResponseEntity
     */
    @ApiOperation(
        notes = 'Method of patching an user.',
        nickname = 'patch',
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = 'patch'
    )
    @ApiResponses(value = [
        @ApiResponse(code = 200, message = 'Success'),
        @ApiResponse(code = 400, message = 'Bad Request'),
        @ApiResponse(code = 401, message = 'Unauthorized'),
        @ApiResponse(code = 403, message = 'Forbidden'),
        @ApiResponse(code = 404, message = 'Not Found'),
        @ApiResponse(code = 500, message = 'Failure')
    ])
    @RequestMapping(
        method = RequestMethod.PATCH,
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = '/{code}'
    )
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> patch(
        @ApiParam(value = 'Code of user', required = true)
        @PathVariable Long code,
        @ApiParam(value = 'User data to be modified', required = true)
        @RequestBody UserPatchRequest userPatchRequest) {

        log.info "Modify user ${code} with ${userPatchRequest}"

        User user = userModifyService.patch(code, userPatchRequest)

        log.info "Modified user: ${user}"

        return new ResponseEntity(HttpStatus.OK)
    }
}
