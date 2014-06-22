package de.comprot.facade.v1.controller

import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.MappingService
import de.comprot.facade.Version
import de.comprot.facade.v1.model.SuggestionDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping('api/suggestions')
@RestController class SuggestionsController {

    @Autowired EntityIndexService service

    @Autowired MappingService mappingService

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = Version.V1)
    def get(@RequestParam(value = "filter", required = true) String query) {
        mappingService.map(service.getSuggestions(query), SuggestionDto.class)
    }

}
