package de.comprot.controller

import de.comprot.service.SuggestionService
import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/suggestions")
class SuggestionsController {

    @Autowired SuggestionService service;

    @RequestMapping(
            value    = "",
            method   = RequestMethod.GET,
            produces = "application/json")
    String getJson(@RequestParam(value = "query", required = true) String query) {
        new JsonBuilder(service.getSuggestions(query)).toPrettyString();
    }

}
