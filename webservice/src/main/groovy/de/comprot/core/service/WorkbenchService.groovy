package de.comprot.core.service

import de.comprot.core.model.WorkbenchEntity
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize

interface WorkbenchService {

    @PreAuthorize('isAuthenticated()')
    @PostAuthorize('\
        hasRole("ROLE_ADMIN") \
        or returnObject.user.id == principal.id \
    ') WorkbenchEntity loadById(Long id)

}
