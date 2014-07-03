package de.comprot.core.service

import de.comprot.core.model.ComprotEntity
import de.comprot.core.model.SuggestionEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize

interface EntityIndexService {

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void save(Collection<ComprotEntity> entities)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void deleteAll(ComprotEntity.Type entityType)

    @PreAuthorize('permitAll')
    List<SuggestionEntity> getSuggestions(String query, int limit)

    @PreAuthorize('permitAll')
    Page<ComprotEntity> search(String query, Pageable pageable)

}