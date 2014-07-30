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
        def did = ComprotEntityResourceAssembler.disassembleId(id)
        comprotEntityResourceAssembler.toResource entityService.getEntity(did.type, did.comprotId)
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{id}/bindings', method = RequestMethod.GET, produces = Version.V1)
    def getBindings( @PathVariable('id') String id,
                     @PageableDefault Pageable pageable) {
        def did = ComprotEntityResourceAssembler.disassembleId(id)
        def bindings

        switch (did.type) {
            case ComprotEntity.Type.DRUG:
                bindings = bindingService.getBindingsForDrug did.comprotId, pageable
                break

            case ComprotEntity.Type.PROTEIN:
            default:
                bindings = bindingService.getBindingsForProtein did.comprotId, pageable
                break
        }

        pagedAssembler.toResource bindings, bindingEntityResourceAssembler
    }

}
