package de.comprot.facade.v1.assembler

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.MappingService
import de.comprot.facade.v1.controller.EntityController
import de.comprot.facade.v1.model.ComprotEntityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

@Component class ComprotEntityResourceAssembler extends ResourceAssemblerSupport<ComprotEntity, ComprotEntityDto> {

    @Autowired MappingService mappingService

    ComprotEntityResourceAssembler() {
        super(EntityController, ComprotEntityDto)
    }

    @Override ComprotEntityDto toResource(ComprotEntity entity) {
        createResourceWithId(entity.id, entity)
    }

    @Override protected ComprotEntityDto instantiateResource(ComprotEntity entity) {
        mappingService.generate(entity, ComprotEntityDto)
    }

}
