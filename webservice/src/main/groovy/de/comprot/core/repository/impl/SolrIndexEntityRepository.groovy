package de.comprot.core.repository.impl

import de.comprot.core.model.IndexEntity
import de.comprot.core.repository.IndexEntityRepository
import org.springframework.data.solr.repository.support.SimpleSolrRepository
import org.springframework.stereotype.Repository

@Repository class SolrIndexEntityRepository
        extends SimpleSolrRepository<IndexEntity, String> implements IndexEntityRepository {}
