package de.comprot.facade.v1.controller

import de.comprot.core.service.ComprotEntityIndexService
import de.comprot.facade.v1.assembler.EntityResourceAssembler
import de.comprot.facade.Version
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping('api/entities')
@RestController class EntityController {

    @Autowired ComprotEntityIndexService service

    @Autowired PagedResourcesAssembler pagedAssembler

    @Autowired EntityResourceAssembler resourceAssembler

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = Version.V1)
    def search( @RequestParam(value = "q", required = true) String query,
                @PageableDefault Pageable pageable) {
        pagedAssembler.toResource service.search(query, pageable), resourceAssembler
    }

}
