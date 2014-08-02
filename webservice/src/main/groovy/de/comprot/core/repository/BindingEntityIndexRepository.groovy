package de.comprot.core.repository

import de.comprot.core.model.BindingEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.solr.repository.Query
import org.springframework.data.solr.repository.SolrCrudRepository

interface BindingEntityIndexRepository extends SolrCrudRepository<BindingEntity, String> {

    @Query('targetId:?0 OR compoundId:?0')
    Page<BindingEntity> findByEntity(String id, Pageable pageable)

}
