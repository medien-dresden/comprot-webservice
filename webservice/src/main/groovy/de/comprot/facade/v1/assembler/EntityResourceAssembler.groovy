package de.comprot.facade.v1.assembler

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.MappingService
import de.comprot.facade.v1.controller.EntityController
import de.comprot.facade.v1.model.EntityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

@Component class EntityResourceAssembler extends ResourceAssemblerSupport<ComprotEntity, EntityDto> {

    @Autowired MappingService mappingService

    EntityResourceAssembler() {
        super(EntityController, EntityDto)
    }

    @Override EntityDto toResource(ComprotEntity entity) {
        createResourceWithId(entity.entityId, entity)
    }

    @Override protected EntityDto instantiateResource(ComprotEntity entity) {
        mappingService.generate(entity, EntityDto)
    }

}
