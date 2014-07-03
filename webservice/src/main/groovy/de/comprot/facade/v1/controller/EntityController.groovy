package de.comprot.facade.v1.controller

import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.MappingService
import de.comprot.facade.Version
import de.comprot.facade.v1.model.EntityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping('api/entities')
@RestController class EntityController {

    @Autowired EntityIndexService service

    @Autowired MappingService mappingService

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = Version.V1)
    def search( @RequestParam(value = "q", required = true) String query,
                @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
        mappingService.generate service.search(query, page, size), EntityDto
    }

}
