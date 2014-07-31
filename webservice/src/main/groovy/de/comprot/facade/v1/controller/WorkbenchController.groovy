package de.comprot.facade.v1.controller

import de.comprot.core.service.WorkbenchService
import de.comprot.facade.Version
import de.comprot.facade.v1.assembler.WorkbenchResourceAssembler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RequestMapping('api/workbenches')
@RestController class WorkbenchController {

    @Autowired WorkbenchResourceAssembler resourceAssembler

    @Autowired WorkbenchService workbenchService

    @RequestMapping(value = '{id}', method = RequestMethod.GET, produces = Version.V1)
    def getOne(@PathVariable Long id) {
        resourceAssembler.toResource workbenchService.loadById(id)
    }

}
