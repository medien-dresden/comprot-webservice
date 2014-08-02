package de.comprot.facade.v1.controller

import de.comprot.core.model.WorkbenchEntity
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
    def getOne(@PathVariable Long id) {
        resourceAssembler.toResource workbenchService.loadById(id)
    }

    @SuppressWarnings('GroovyAssignabilityCheck')
    @RequestMapping(value = '{id}', method = RequestMethod.PUT, produces = Version.V1, consumes = Version.V1)
    def patch(@Valid @RequestBody WorkbenchDto workbenchDto, @PathVariable Long id) {
        def workbench = mappingService.generate workbenchDto, WorkbenchEntity
        println workbench
    }

}
