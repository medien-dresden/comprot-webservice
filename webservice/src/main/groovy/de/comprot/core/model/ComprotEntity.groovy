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
class ComprotEntity {

    /**
     * Type is used for resource id
     */
    enum Type { TARGET, COMPOUND }

    /**
     * ID of this entity
     */
    @Id String id

	/**
	 * (NCBI) taxonomy id (null/not relevant for some entity types (e.g. for compounds))
	 */
    @Indexed(type = 'int') Integer taxonomyId

	/**
	 * the id of this entity in its source database
	 */
    @Indexed(type = 'string_ci', copyTo = 'suggestSource') String sourceId

	/**
	 * the primary name
	 */
    @Indexed(type = 'text_general', copyTo = 'suggestSource') String name

	/**
	 * known secondary names or synonyms
	 */
    @Indexed(type = 'text_general', copyTo = 'suggestSource') String[] synonyms = []

    /**
     * Type of the entity
     */
    @Indexed(type = 'enum') Type type

    /**
     * Entity popularity
     */
    @Indexed(type = 'int') Integer popularity = 0

}
