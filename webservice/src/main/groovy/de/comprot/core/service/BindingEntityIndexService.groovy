package de.comprot.core.service

import de.comprot.core.model.BindingEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize

interface BindingEntityIndexService {

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void save(Collection<BindingEntity> entities)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void deleteAll()

    @PreAuthorize('permitAll')
    Page<BindingEntity> getBindingsForDrug(Long comprotId, Pageable pageable)

    @PreAuthorize('permitAll')
    Page<BindingEntity> getBindingsForProtein(Long comprotId, Pageable pageable)

}