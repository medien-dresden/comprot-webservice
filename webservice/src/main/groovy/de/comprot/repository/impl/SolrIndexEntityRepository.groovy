package de.comprot.repository.impl

import de.comprot.model.IndexEntity
import de.comprot.repository.IndexEntityRepository
import org.springframework.data.solr.repository.support.SimpleSolrRepository
import org.springframework.stereotype.Repository

@Repository('indexEntityRepository') class SolrIndexEntityRepository
        extends SimpleSolrRepository<IndexEntity, String> implements IndexEntityRepository {}
