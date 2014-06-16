package de.comprot.core.repository

import de.comprot.core.model.IndexEntity
import org.springframework.data.solr.repository.SolrCrudRepository

interface IndexEntityRepository extends SolrCrudRepository<IndexEntity, String> {}
