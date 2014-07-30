package de.comprot.facade.v1.assembler

import de.comprot.core.model.BindingEntity
import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.MappingService
import de.comprot.facade.v1.controller.BindingController
import de.comprot.facade.v1.controller.EntityController
import de.comprot.facade.v1.model.BindingEntityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

@Component class BindingEntityResourceAssembler extends ResourceAssemblerSupport<BindingEntity, BindingEntityDto> {

    @Autowired MappingService mappingService

    BindingEntityResourceAssembler() {
        super(BindingController, BindingEntityDto)
    }

    @SuppressWarnings("UnnecessaryQualifiedReference")
    @Override BindingEntityDto toResource(BindingEntity entity) {
        def dto = createResourceWithId(entity.entityId, entity)

        dto.add(linkTo(EntityController).slash(
                ComprotEntityResourceAssembler.assembleId(new ComprotEntity(
                    type: ComprotEntity.Type.PROTEIN,
                    comprotId: entity.targetId
                ))).withRel('target'))

        dto.add(linkTo(EntityController).slash(
                ComprotEntityResourceAssembler.assembleId(new ComprotEntity(
                    type: ComprotEntity.Type.DRUG,
                    comprotId: entity.compoundId
                ))).withRel('compound'))

        return dto
    }

    @Override protected BindingEntityDto instantiateResource(BindingEntity entity) {
        mappingService.generate(entity, BindingEntityDto)
    }

}
