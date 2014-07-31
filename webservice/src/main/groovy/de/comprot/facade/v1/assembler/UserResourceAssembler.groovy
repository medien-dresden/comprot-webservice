package de.comprot.facade.v1.assembler

import de.comprot.core.model.UserEntity
import de.comprot.core.service.MappingService
import de.comprot.facade.v1.controller.UserController
import de.comprot.facade.v1.model.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

@Component class UserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserDto> {

    @Autowired MappingService mappingService

    UserResourceAssembler() {
        super(UserController, UserDto)
    }

    @Override UserDto toResource(UserEntity user) {
        def dto = createResourceWithId(user.username, user)

        dto.add(linkTo(UserController)
                .slash(user.username)
                .slash('workbenches')
                .withRel('workbenches'))

        return dto
    }

    @Override protected UserDto instantiateResource(UserEntity user) {
        mappingService.generate(user, UserDto)
    }

}
