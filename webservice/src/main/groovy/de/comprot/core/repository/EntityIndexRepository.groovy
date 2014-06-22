package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.solr.repository.Query
import org.springframework.data.solr.repository.SolrCrudRepository

interface EntityIndexRepository extends SolrCrudRepository<ComprotEntity, String> {

    @Query( value = 'suggest_query:*?0*',
            requestHandler = '/suggest',
            fields = [  ComprotEntity.FIELD_NAME,
                        ComprotEntity.FIELD_SYNONYMS,
                        ComprotEntity.FIELD_TYPE ])
    Page<ComprotEntity> findSuggestions(String query, Pageable pageable)

    void deleteByType(ComprotEntity.Type entityType)

}
