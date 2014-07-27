package de.comprot.core.service

import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize

interface BindingEntitySourceService {

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    Page<ComprotEntity> getBindings(Pageable pageable)

}