package de.comprot.facade.v1.assembler

import de.comprot.core.model.UserEntity
import de.comprot.core.service.MappingService
import de.comprot.facade.v1.controller.UserController
import de.comprot.facade.v1.model.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

@Component class UserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserDto> {

    @Autowired MappingService mappingService

    UserResourceAssembler() {
        super(UserController, UserDto)
    }

    @Override UserDto toResource(UserEntity user) {
        createResourceWithId(user.username, user)
    }

    @Override protected UserDto instantiateResource(UserEntity user) {
        mappingService.generate(user, UserDto)
    }

}
