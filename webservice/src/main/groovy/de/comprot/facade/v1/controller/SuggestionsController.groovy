package de.comprot.facade.v1.controller

import de.comprot.facade.Version
import de.comprot.core.service.SuggestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping(value = "api/suggestions",
        produces = Version.V1, consumes = Version.V1)
@RestController class SuggestionsController {

    @Autowired SuggestionService service;

    @RequestMapping(method = RequestMethod.GET)
    def getSuggestions(@RequestParam(value = "filter", required = true) String query) {
        service.getSuggestions(query)
    }

}
