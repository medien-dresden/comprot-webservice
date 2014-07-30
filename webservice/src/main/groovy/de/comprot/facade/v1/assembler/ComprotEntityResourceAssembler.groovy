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
        def dto = createResourceWithId(assembleId(entity), entity)

        dto.add(linkTo(EntityController)
                .slash(assembleId(entity))
                .slash('bindings')
                .withRel('bindings'))

        return dto
    }

    @Override protected ComprotEntityDto instantiateResource(ComprotEntity entity) {
        mappingService.generate(entity, ComprotEntityDto)
    }

    static String assembleId(ComprotEntity entity) {
        "${entity.type.name()[0]}${entity.comprotId}"
    }

    @SuppressWarnings("GroovyUnusedAssignment")
    static def disassembleId(String id) {
        def (_, type, comprotId) = (id =~ /^(\w)(\d+)$/)[0]

        switch (type) {
            case "C": type = "COMPOUND"
                break

            case "T":
            default: type = "TARGET"
                break
        }

        [
            comprotId:  comprotId as Long,
            type:       type as ComprotEntity.Type
        ]
    }

}
