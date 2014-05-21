package de.comprot.controller

import groovy.json.JsonBuilder

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/suggestions")
class SuggestionsController {

    @RequestMapping(
            value    = "",
            method   = RequestMethod.GET,
            produces = "application/json")
    String getJson(@RequestParam(value = "query", required = true) String query) {
        new JsonBuilder([
                    {
                        'label' query + ' Protein 564'
                        'hits' 1456
                    }, {
                        'label' query + ' Target 23'
                        'hits' 8946
                    }, {
                        'label' query + ' Target 65465'
                        'hits' 654
                    }
            ]).toPrettyString()
    }

}
