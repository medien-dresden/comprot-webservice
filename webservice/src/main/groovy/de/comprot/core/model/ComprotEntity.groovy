package de.comprot.core.model

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.solr.core.mapping.Indexed
import org.springframework.data.solr.core.mapping.SolrDocument

/**
 * Represents a single compound or target.
 * All field types are defined in solr schema.xml.
 */
@EqualsAndHashCode(
        includeFields = true,
        excludes = ['name', 'synonyms'])
@SolrDocument(solrCoreName = 'comprotentity')
class ComprotEntity implements Serializable {

    /**
     * Type is used for resource id
     */
    enum Type { TARGET, COMPOUND }

	/**
	 * ID of this entity within the index
	 */
	@Id transient String indexId

	/**
	 * internal id of this entity in the comprot database
	 * NOTE: this id is only unique together with the entityType!
	 */
    @Indexed(type = 'long') Long comprotId

	/**
	 * (NCBI) taxonomy id (null/not relevant for some entity types (e.g. for compounds))
	 */
    @Indexed(type = 'int') transient Integer taxonomyId

	/**
	 * the id of this entity in its source database
	 */
    @Indexed(type = 'string_ci', copyTo = 'suggestSource') transient String sourceId

	/**
	 * the primary name
	 */
    @Indexed(type = 'text_general', copyTo = 'suggestSource') transient String name

	/**
	 * known secondary names or synonyms
	 */
    @Indexed(type = 'text_general', copyTo = 'suggestSource') transient String[] synonyms = []

    /**
     * Type of the entity
     */
    @Indexed(type = 'enum') Type type

}
