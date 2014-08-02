package de.comprot.facade.v1.assembler

import de.comprot.core.model.WorkbenchEntity
import de.comprot.core.service.ComprotEntityIndexService
import de.comprot.core.service.MappingService
import de.comprot.facade.v1.controller.UserController
import de.comprot.facade.v1.controller.WorkbenchController
import de.comprot.facade.v1.model.WorkbenchDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

@Component class WorkbenchResourceAssembler extends ResourceAssemblerSupport<WorkbenchEntity, WorkbenchDto> {

    @Autowired MappingService mappingService

    @Autowired ComprotEntityIndexService indexService

    @Autowired ComprotEntityResourceAssembler entityResourceAssembler

    WorkbenchResourceAssembler() {
        super(WorkbenchController, WorkbenchDto)
    }

    @Override WorkbenchDto toResource(WorkbenchEntity workbench) {
        def dto = createResourceWithId workbench.id, workbench

        dto.targets = []
        dto.compounds = []

        ['targets', 'compounds'].each {
            workbench."$it".each { id ->
                //noinspection GroovyAssignabilityCheck
                dto."$it".add entityResourceAssembler.toResource(indexService.getEntity(id as String))
            }
        }

        dto.add(linkTo(UserController)
                .slash(workbench.user.username)
                .withRel('owner'))

        return dto
    }

    @Override protected WorkbenchDto instantiateResource(WorkbenchEntity workbench) {
        mappingService.generate workbench, WorkbenchDto
    }

}
