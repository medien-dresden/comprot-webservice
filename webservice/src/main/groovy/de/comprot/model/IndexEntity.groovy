package de.comprot.model

import org.apache.solr.client.solrj.beans.Field
import org.springframework.data.annotation.Id

/**
 * represents a single drug, protein or disease
 */
class IndexEntity {

    enum Type {
        PROTEIN,
        DISEASE,
        DRUG
    }

	/**
	 * internal unique search id of this entity 
	 * this id will be unique for all entities (across all EntityType's)
	 * it will normally generated/set only for those entities that are obtained through a SearchResult's SearchHit
	 */
	@Id @Field Long id

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
    @Field Collection<String> synonyms = []

    /**
     * Type of the entity
     */
    @Field Type type

}
