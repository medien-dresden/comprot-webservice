package de.comprot.controller

import com.fasterxml.jackson.annotation.JsonView
import de.comprot.model.User
import de.comprot.model.Views
import de.comprot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RequestMapping("api/users")
@RestController class UserController {

    @Autowired UserService service;

    @JsonView(Views.Public.class)
    @RequestMapping(method = RequestMethod.POST)
    def post(@Valid @RequestBody User user) {
        service.register(user)
        new ResponseEntity<User>(user,
                new HttpHeaders().with {
                    location: "api/users/${user.getUsername()}"
                    return it }, HttpStatus.CREATED)
    }

    @JsonView(Views.Owner.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{username}', method = RequestMethod.GET)
    def get(@PathVariable String username) {
        service.loadByUsername(username)
    }

}
