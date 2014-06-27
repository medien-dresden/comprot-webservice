package de.comprot.core.service

import de.comprot.core.model.UserEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize

interface UserService {

    @PreAuthorize('\
        hasRole("ROLE_ADMIN") \
        or isAnonymous() \
    ') void register(UserEntity user)

    @PreAuthorize('\
        hasRole("ROLE_ADMIN") \
        or #username == authentication.name \
        or (#username == "me" and isAuthenticated()) \
    ') UserEntity loadByUsername(String username)

}