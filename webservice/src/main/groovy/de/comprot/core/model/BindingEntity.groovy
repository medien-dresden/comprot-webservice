package de.comprot.core.model

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.solr.core.mapping.Indexed
import org.springframework.data.solr.core.mapping.SolrDocument

/**
 * Represents the binding between drugs and proteins.
 * All field types are defined in solr schema.xml.
 */
@EqualsAndHashCode(
        includeFields = true,
        excludes = [])
@SolrDocument(solrCoreName = 'bindingentity')
class BindingEntity {

    /**
     * ID of this entity within the index
     */
    @Id String indexId

	/**
	 * Binding id
	 */
    @Indexed(type = 'long') Long entityId

	/**
	 * Protein id
	 */
    @Indexed(type = 'long') Long targetId

    /**
     * Drug id
     */
    @Indexed(type = 'long') Long compoundId

    /**
     * Interaction type
     */
    @Indexed(type = 'text_general', copyTo = 'suggestSource') String interactionType

}
