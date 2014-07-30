package de.comprot.core.repository

import de.comprot.core.model.BindingEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.solr.repository.SolrCrudRepository

interface BindingEntityIndexRepository extends SolrCrudRepository<BindingEntity, String> {

    Page<BindingEntity> findByTargetId(Long entityId, Pageable pageable)

    Page<BindingEntity> findByCompoundId(Long entityId, Pageable pageable)

}
