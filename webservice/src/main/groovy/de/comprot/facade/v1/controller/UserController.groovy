package de.comprot.facade.v1.controller

import de.comprot.core.model.UserEntity
import de.comprot.core.service.MappingService
import de.comprot.core.service.UserService
import de.comprot.facade.Version
import de.comprot.facade.v1.assembler.UserResourceAssembler
import de.comprot.facade.v1.assembler.WorkbenchResourceAssembler
import de.comprot.facade.v1.model.RegistrationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RequestMapping('api/users')
@RestController class UserController {

    @Autowired UserService userService

    @Autowired UserResourceAssembler userResourceAssembler

    @Autowired WorkbenchResourceAssembler workbenchResourceAssembler

    @Autowired MappingService mappingService

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = Version.V1, produces = Version.V1)
    def register(@Valid @RequestBody RegistrationDto registration) {
        def user = mappingService.generate registration, UserEntity

        userService.register user
        userResourceAssembler.toResource user
    }

    @RequestMapping(value = '{username}', method = RequestMethod.GET, produces = Version.V1)
    def getOne(@PathVariable String username) {
        userResourceAssembler.toResource userService.loadByUsername(username)
    }

    @RequestMapping(value = '{username}/workbenches', method = RequestMethod.GET, produces = Version.V1)
    def getWorkbenches(@PathVariable String username) {
        workbenchResourceAssembler.toResources userService.loadByUsername(username).workbenches
    }

}
