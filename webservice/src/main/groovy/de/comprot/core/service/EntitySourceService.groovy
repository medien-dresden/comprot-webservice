package de.comprot.core.service

import de.comprot.core.model.ComprotEntity
import de.comprot.common.Page
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize

interface EntitySourceService {

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    Page<ComprotEntity> getProteins(Pageable pageable)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    Page<ComprotEntity> getDrugs(Pageable pageable)

}