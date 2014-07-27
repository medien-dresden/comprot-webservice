package de.comprot.core.service

import de.comprot.core.model.BindingEntity
import org.springframework.security.access.prepost.PreAuthorize

interface BindingEntityIndexService {

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void save(Collection<BindingEntity> entities)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void deleteAll()

}