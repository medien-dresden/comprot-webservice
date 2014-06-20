package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.solr.repository.SolrCrudRepository

interface ComprotEntityRepository extends SolrCrudRepository<ComprotEntity, String> {}
