package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.solr.repository.SolrCrudRepository

interface ComprotEntityIndexRepository extends SolrCrudRepository<ComprotEntity, String> {}
