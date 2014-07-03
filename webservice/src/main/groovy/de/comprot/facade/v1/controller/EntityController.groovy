package de.comprot.facade.v1.controller

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.MappingService
import de.comprot.facade.Version
import de.comprot.facade.v1.model.EntityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping('api/entities')
@RestController class EntityController extends IdentifiableResourceAssemblerSupport<ComprotEntity, EntityDto> {

    @Autowired EntityIndexService service

    @Autowired PagedResourcesAssembler pageableAssembler

    @Autowired MappingService mappingService

    EntityController() { super(EntityController, EntityDto) }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = Version.V1)
    def search( @RequestParam(value = "q", required = true) String query,
                @PageableDefault Pageable pageable) {
        pageableAssembler.toResource service.search(query, pageable), this
    }

    @Override EntityDto toResource(ComprotEntity entity) { createResource(entity) }

    @Override protected EntityDto instantiateResource(ComprotEntity entity) {
        mappingService.generate entity, EntityDto
    }

}
