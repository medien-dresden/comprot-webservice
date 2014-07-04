package de.comprot.facade.v1.controller

import de.comprot.core.model.UserEntity
import de.comprot.core.service.MappingService
import de.comprot.core.service.UserService
import de.comprot.facade.Version
import de.comprot.facade.v1.assembler.UserResourceAssembler
import de.comprot.facade.v1.model.RegistrationDto
import de.comprot.facade.v1.model.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

@RequestMapping('api/users')
@RestController class UserController {

    @Autowired UserService userService

    @Autowired UserResourceAssembler resourceAssembler

    @Autowired MappingService mappingService

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = Version.V1, produces = Version.V1)
    def register(@Valid @RequestBody RegistrationDto registration) {
        def user = mappingService.generate registration, UserEntity

        userService.register user
        resourceAssembler.toResource(user)
    }

    @RequestMapping(value = '{username}', method = RequestMethod.GET, produces = Version.V1)
    def getOne(@PathVariable String username) {
        resourceAssembler.toResource userService.loadByUsername(username)
    }

}
