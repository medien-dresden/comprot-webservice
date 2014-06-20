package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.solr.repository.Query
import org.springframework.data.solr.repository.SolrCrudRepository

interface EntityIndexRepository extends EntityIndexDeletionRepository, SolrCrudRepository<ComprotEntity, String> {

    // FIXME this is NOT the right way, since a full search is triggered
    @Query('term_suggest:*?0*') List<ComprotEntity> findSuggestions(String query)

}
