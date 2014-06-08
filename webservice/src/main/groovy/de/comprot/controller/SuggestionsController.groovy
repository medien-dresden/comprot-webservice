package de.comprot.controller

import de.comprot.service.SuggestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("suggestions")
@RestController class SuggestionsController {

    @Autowired SuggestionService service;

    @RequestMapping(method = RequestMethod.GET)
    def getSuggestions(@RequestParam(value = "filter", required = true) String query) {
        service.getSuggestions(query)
    }

}
