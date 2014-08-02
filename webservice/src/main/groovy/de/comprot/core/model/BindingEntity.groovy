package de.comprot.core.model

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.solr.core.mapping.Indexed
import org.springframework.data.solr.core.mapping.SolrDocument

/**
 * Represents the binding between compounds and targets.
 * All field types are defined in solr schema.xml.
 */
@EqualsAndHashCode(
        includeFields = true,
        excludes = [])
@SolrDocument(solrCoreName = 'bindingentity')
class BindingEntity {

	/**
	 * Binding id
	 */
    @Id String id

	/**
	 * Target id
	 */
    @Indexed(type = 'string') String targetId

    /**
     * Compound id
     */
    @Indexed(type = 'string') String compoundId

    /**
     * Interaction type
     */
    @Indexed(type = 'text_general', copyTo = 'suggestSource') String interactionType

}
