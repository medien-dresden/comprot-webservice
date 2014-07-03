package de.comprot.facade.v1.controller

import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.MappingService
import de.comprot.facade.Version
import de.comprot.facade.v1.model.EntityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.ResourceAssembler
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping('api/entities')
@RestController class EntityController {

    @Autowired EntityIndexService service

    @Autowired MappingService mappingService

    @Autowired PagedResourcesAssembler assembler

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = Version.V1)
    def search( @RequestParam(value = "q", required = true) String query,
                @PageableDefault Pageable pageable) {
        assembler.toResource(service.search(query, pageable),
                { mappingService.generate(it, EntityDto) } as ResourceAssembler)
    }

}
