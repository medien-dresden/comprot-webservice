package de.comprot.core.model

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.solr.core.mapping.Indexed
import org.springframework.data.solr.core.mapping.SolrDocument

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

/**
 * Represents a single compound or target.
 * All field types are defined in solr schema.xml.
 */
@EqualsAndHashCode(
        includeFields = true,
        excludes = ['name', 'synonyms'])
@SolrDocument(solrCoreName = 'comprotentity')
@Table(name = 'comprot_entity')
@Entity class ComprotEntity {

    /**
     * Type is used for resource id
     */
    enum Type { TARGET, COMPOUND }

    /**
     * ID of this entity within the index
     */
    @Id String indexId

	/**
	 * ID of this entity within the application database
	 */
    @javax.persistence.Id
	String getId() { "${type}-${comprotId}" }

    void setId(String id) {
        def (typeString, comprotIdString) = id.tokenize('-')
        type = typeString as Type
        comprotId = comprotIdString as Long
    }

	/**
	 * internal id of this entity in the comprot database
	 * NOTE: this id is only unique together with the entityType!
	 */
    @Indexed(type = 'long') Long comprotId

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
    @Enumerated(EnumType.STRING)
    @Indexed(type = 'enum') Type type

}
