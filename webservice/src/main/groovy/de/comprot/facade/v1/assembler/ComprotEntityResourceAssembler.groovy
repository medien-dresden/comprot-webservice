package de.comprot.facade.v1.assembler

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.MappingService
import de.comprot.facade.v1.controller.EntityController
import de.comprot.facade.v1.model.ComprotEntityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

@Component class ComprotEntityResourceAssembler extends ResourceAssemblerSupport<ComprotEntity, ComprotEntityDto> {

    @Autowired MappingService mappingService

    ComprotEntityResourceAssembler() {
        super(EntityController, ComprotEntityDto)
    }

    @Override ComprotEntityDto toResource(ComprotEntity entity) {
        def dto = createResourceWithId(entity.id, entity)

        dto.add(linkTo(EntityController)
                .slash(entity.id)
                .slash('bindings')
                .withRel('bindings'))

        if (dto.name == null && dto.sourceId != null) {
            dto.name = dto.sourceId
        }

        return dto
    }

    @Override protected ComprotEntityDto instantiateResource(ComprotEntity entity) {
        mappingService.generate(entity, ComprotEntityDto)
    }

}
