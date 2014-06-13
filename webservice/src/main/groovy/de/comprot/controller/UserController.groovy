package de.comprot.controller

import de.comprot.UsernameAlreadyTakenException
import de.comprot.model.EntityValidationError
import de.comprot.model.User
import de.comprot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RequestMapping("api/users")
@RestController class UserController {

    @Autowired UserService service;

    @RequestMapping(method = RequestMethod.POST)
    def register(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new BindException(result)
        }

        try {
            service.register(user)
            new ResponseEntity<User>(user,
                    new HttpHeaders().with {
                        location: "api/users/${user.getUsername()}"
                        return it }, HttpStatus.CREATED)

        } catch (UsernameAlreadyTakenException ignored) {
            new ResponseEntity<EntityValidationError>(
                    new EntityValidationError(field: 'username', message: 'already taken'), HttpStatus.CONFLICT)
        }
    }

}
