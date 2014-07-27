package de.comprot.core.repository

import de.comprot.core.model.BindingEntity
import org.springframework.data.solr.repository.SolrCrudRepository

interface BindingEntityIndexRepository extends SolrCrudRepository<BindingEntity, String> {

}
