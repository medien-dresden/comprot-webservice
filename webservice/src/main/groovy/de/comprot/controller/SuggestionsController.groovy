package de.comprot.controller

import groovy.json.JsonBuilder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/suggestions")
class SuggestionsController {

    @RequestMapping(
            value    = "/{query}",
            method   = RequestMethod.GET,
            produces = "application/json")
    String getJson(@PathVariable("query") String query) {
        new JsonBuilder(
            suggestions: [
                    {
                        'label' query + '-suggestion-a'
                        'hits' 1456
                    }, {
                        'label' query + '-suggestion-b'
                        'hits' 8946
                    }, {
                        'label' query + '-suggestion-c'
                        'hits' 654
                    }
            ]).toPrettyString()
    }

}
