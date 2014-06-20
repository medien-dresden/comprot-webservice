package de.comprot.facade.v1.controller

import de.comprot.core.model.UserEntity
import de.comprot.core.service.MappingService
import de.comprot.core.service.UserService
import de.comprot.facade.Version
import de.comprot.facade.v1.model.RegistrationDto
import de.comprot.facade.v1.model.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RequestMapping(value = 'api/users',
        produces = Version.V1, consumes = Version.V1)
@RestController class UsersController {

    @Autowired UserService userService

    @Autowired MappingService mappingService

    @RequestMapping(method = RequestMethod.POST)
    def post(@Valid @RequestBody RegistrationDto registration) {
        def user = mappingService.map(registration, UserEntity.class)
        userService.register(user)
        new ResponseEntity<UserDto>(mappingService.map(user, UserDto.class),
                new HttpHeaders().with {
                    location: "api/users/${user.getUsername()}"
                    return it }, HttpStatus.CREATED)
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{username}', method = RequestMethod.GET)
    def get(@PathVariable String username) {
        mappingService.map(userService.loadByUsername(username), UserDto.class)
    }

}
