package de.comprot.core.service

import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize

interface ComprotEntitySourceService {

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    Page<ComprotEntity> getTargets(Pageable pageable)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    Page<ComprotEntity> getCompounds(Pageable pageable)

}