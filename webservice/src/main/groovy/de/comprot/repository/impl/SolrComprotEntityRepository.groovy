package de.comprot.repository.impl

import de.comprot.model.ComprotEntity
import de.comprot.repository.ComprotEntityRepository
import org.springframework.data.solr.repository.support.SimpleSolrRepository
import org.springframework.stereotype.Repository

@Repository class SolrComprotEntityRepository
        extends SimpleSolrRepository<ComprotEntity, String> implements ComprotEntityRepository {}
