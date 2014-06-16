package de.comprot.facade.v1.controller

import de.comprot.core.service.MappingService
import de.comprot.core.service.SuggestionService
import de.comprot.facade.Version
import de.comprot.facade.v1.model.Suggestion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping(value = "api/suggestions",
        produces = Version.V1, consumes = Version.V1)
@RestController class SuggestionsController {

    @Autowired SuggestionService service

    @Autowired MappingService mappingService

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    def get(@RequestParam(value = "filter", required = true) String query) {
        mappingService.map(service.getSuggestions(query), Suggestion.class)
    }

}
