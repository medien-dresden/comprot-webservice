package de.comprot.controller

import de.comprot.model.User
import de.comprot.service.UserService
import org.springframework.validation.BindException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RequestMapping("users")
@RestController class UserController {

    @Autowired UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    def addUser(@Valid @RequestBody User user, BindingResult result, HttpServletResponse response) {
        if (!result.hasErrors()) {
            service.addUser(user)
            response.setHeader("Location", "/users/${user.getUserName()}")
            return user

        } else {
            throw new BindException(result)
        }
    }

}
