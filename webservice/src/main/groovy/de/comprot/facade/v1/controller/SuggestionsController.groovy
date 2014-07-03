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
    def suggest(@RequestParam(value = "q", required = true) String query,
                @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
        mappingService.generateList service.getSuggestions(query, limit), SuggestionDto
    }

}
