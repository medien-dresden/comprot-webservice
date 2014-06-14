package de.comprot.repository

import de.comprot.model.IndexEntity
import org.springframework.data.solr.repository.SolrCrudRepository

interface IndexEntityRepository extends SolrCrudRepository<IndexEntity, String> {}
