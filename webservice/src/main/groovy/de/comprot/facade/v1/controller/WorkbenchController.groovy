package de.comprot.facade.v1.controller

import de.comprot.core.service.MappingService
import de.comprot.core.service.WorkbenchService
import de.comprot.facade.Version
import de.comprot.facade.v1.assembler.WorkbenchResourceAssembler
import de.comprot.facade.v1.model.WorkbenchDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RequestMapping('api/workbenches')
@RestController class WorkbenchController {

    @Autowired WorkbenchResourceAssembler resourceAssembler

    @Autowired WorkbenchService workbenchService

    @Autowired MappingService mappingService

    @RequestMapping(value = '{id}', method = RequestMethod.GET, produces = Version.V1)
    def getOne(@PathVariable('id') Long id) {
        resourceAssembler.toResource workbenchService.loadById(id)
    }

    @SuppressWarnings('GroovyAssignabilityCheck')
    @RequestMapping(value = '{id}', method = RequestMethod.PUT, produces = Version.V1, consumes = Version.V1)
    def put(@Valid @RequestBody WorkbenchDto workbenchDto,
              @PathVariable('id') Long id) {
        def currentWorkbench = workbenchService.loadById id
        mappingService.transfer workbenchDto, currentWorkbench
        workbenchService.save currentWorkbench
        resourceAssembler.toResource currentWorkbench
    }

}
