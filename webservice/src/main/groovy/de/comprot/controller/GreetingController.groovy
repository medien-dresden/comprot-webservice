package de.comprot.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/service/greeting")
class GreetingController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    String greet(@PathVariable("name") String name) {
        "I say hi to $name"
    }

}
