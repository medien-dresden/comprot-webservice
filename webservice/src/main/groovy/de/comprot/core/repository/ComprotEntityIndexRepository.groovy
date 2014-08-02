package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.solr.core.query.result.FacetPage
import org.springframework.data.solr.repository.Facet
import org.springframework.data.solr.repository.Query
import org.springframework.data.solr.repository.SolrCrudRepository

interface ComprotEntityIndexRepository extends SolrCrudRepository<ComprotEntity, String> {

    @Query(value = '*:*', requestHandler = '/suggest')
    @Facet(prefix = '?0', fields = 'suggestSource', limit = 20, minCount = 1)
    FacetPage<ComprotEntity> findSuggestions(String query, Pageable pageable)

    @Query('name:*?0* OR synonyms:*?0* OR sourceId:*?0*')
    Page<ComprotEntity> findByQuery(String query, Pageable pageable)

    ComprotEntity findById(String id)

    void deleteByType(ComprotEntity.Type entityType)

}
