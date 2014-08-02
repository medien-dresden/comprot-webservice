package de.comprot.facade.v1.controller

import de.comprot.facade.Version
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping('api/bindings')
@RestController class BindingController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{id}', method = RequestMethod.GET, produces = Version.V1)
    def getOne( @PathVariable('id') String id) {

    }

}
