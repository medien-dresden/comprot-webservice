package de.comprot.core.model

import groovy.transform.EqualsAndHashCode
import org.apache.solr.client.solrj.beans.Field
import org.springframework.data.annotation.Id

/**
 * represents a single drug, protein or disease
 */
@EqualsAndHashCode(
        includeFields = true,
        excludes = ['name', 'synonyms'])
class ComprotEntity {

    /**
     * First letter is used for resource id
     * and therefore has to be UNIQUE ACROSS ALL TYPES!
     */
    enum Type { PROTEIN, DISEASE, MEDICINE }

	/**
	 * internal unique search id of this entity 
	 * this id will be unique for all entities (across all EntityType's)
	 */
	@Id @Field String id

	/**
	 * internal id of this entity in the comprot database
	 * NOTE: this id is only unique together with the entityType!
	 */
    @Field Long comprotId

	/**
	 * (NCBI) taxonomy id (null/not relevant for some entity types (e.g. for drugs))
	 */
    @Field Integer taxonomyId

	/**
	 * the id of this entity in its source database
	 */
    @Field String sourceId

	/**
	 * the primary name
	 */
    @Field String name

	/**
	 * known secondary names or synonyms
	 */
    @Field String[] synonyms = []

    /**
     * Type of the entity
     */
    @Field Type type

    /** @return unique entity id */
    String getEntityId() { "${type.name()[0].toLowerCase()}${comprotId}" }

}
