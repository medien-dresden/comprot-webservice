package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.solr.core.query.result.FacetPage
import org.springframework.data.solr.repository.Facet
import org.springframework.data.solr.repository.Query
import org.springframework.data.solr.repository.SolrCrudRepository

interface EntityIndexRepository extends SolrCrudRepository<ComprotEntity, String> {

    @Query(value = '*:*', requestHandler = '/suggest')
    @Facet(prefix = '?0', fields = 'suggest_query', limit = 20, minCount = 1)
    FacetPage<ComprotEntity> findSuggestions(String query, Pageable pageable)

    void deleteByType(ComprotEntity.Type entityType)

}
