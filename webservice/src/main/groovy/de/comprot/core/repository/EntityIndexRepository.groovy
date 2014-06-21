package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.solr.core.query.result.FacetPage
import org.springframework.data.solr.repository.Facet
import org.springframework.data.solr.repository.Query
import org.springframework.data.solr.repository.SolrCrudRepository

interface EntityIndexRepository extends SolrCrudRepository<ComprotEntity, String> {

    @Query(value = 'suggest_query:*?0*', requestHandler = '/suggest')
    List<ComprotEntity> findSuggestions(String query)

    @Query(value = 'type:?0', delete = true)
    void deleteAll(ComprotEntity.Type entityType)

}
