package de.comprot.repository

import de.comprot.model.ComprotEntity
import org.springframework.data.solr.repository.SolrCrudRepository

interface ComprotEntityRepository extends SolrCrudRepository<ComprotEntity, String> {}
