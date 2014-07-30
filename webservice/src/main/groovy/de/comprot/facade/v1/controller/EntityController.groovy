package de.comprot.facade.v1.controller

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.BindingEntityIndexService
import de.comprot.core.service.ComprotEntityIndexService
import de.comprot.facade.v1.assembler.BindingEntityResourceAssembler
import de.comprot.facade.v1.assembler.ComprotEntityResourceAssembler
import de.comprot.facade.Version
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping('api/entities')
@RestController class EntityController {

    @Autowired ComprotEntityIndexService entityService

    @Autowired BindingEntityIndexService bindingService

    @Autowired PagedResourcesAssembler pagedAssembler

    @Autowired ComprotEntityResourceAssembler comprotEntityResourceAssembler

    @Autowired BindingEntityResourceAssembler bindingEntityResourceAssembler

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = Version.V1)
    def search( @RequestParam(value = "q", required = true) String query,
                @PageableDefault Pageable pageable) {
        pagedAssembler.toResource entityService.search(query, pageable), comprotEntityResourceAssembler
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{id}', method = RequestMethod.GET, produces = Version.V1)
    def getOne( @PathVariable('id') String id) {
        def (typeString, comprotIdString) = id.tokenize('-')
        comprotEntityResourceAssembler.toResource entityService.getEntity(
                typeString as ComprotEntity.Type,
                comprotIdString as Long)
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{id}/bindings', method = RequestMethod.GET, produces = Version.V1)
    def getBindings( @PathVariable('id') String id,
                     @PageableDefault Pageable pageable) {
        def (typeString, comprotIdString) = id.tokenize('-')
        def comprotId = comprotIdString as Long
        def bindings

        switch (typeString as ComprotEntity.Type) {
            case ComprotEntity.Type.DRUG:
                bindings = bindingService.getBindingsForDrug comprotId, pageable
                break

            case ComprotEntity.Type.PROTEIN:
            default:
                bindings = bindingService.getBindingsForProtein comprotId, pageable
                break
        }

        pagedAssembler.toResource bindings, bindingEntityResourceAssembler
    }

}
