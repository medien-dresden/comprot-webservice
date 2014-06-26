package de.comprot.core.service

import de.comprot.core.model.UserEntity
import org.springframework.security.access.prepost.PreAuthorize

interface UserService {

    @PreAuthorize('isAnonymous() or hasRole("ROLE_ADMIN")')
    void register(UserEntity user)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    UserEntity loadByUsername(String username)

}