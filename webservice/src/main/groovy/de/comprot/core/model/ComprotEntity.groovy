package de.comprot.core.model

import org.apache.solr.client.solrj.beans.Field
import org.springframework.data.annotation.Id
import org.springframework.data.solr.core.mapping.Indexed

/**
 * represents a single drug, protein or disease
 */
class ComprotEntity {

    public static final String FIELD_ID = 'id'

    public static final String FIELD_COMPROT_ID = 'comprotId'

    public static final String FIELD_TAXONOMY_ID = 'taxonomyId'

    public static final String FIELD_SOURCE_ID = 'sourceId'

    public static final String FIELD_NAME = 'name'

    public static final String FIELD_SYNONYMS = 'synonyms'

    public static final String FIELD_TYPE = 'type'

    enum Type { PROTEIN, DISEASE, DRUG }

	/**
	 * internal unique search id of this entity 
	 * this id will be unique for all entities (across all EntityType's)
	 * it will normally generated/set only for those entities that are obtained through a SearchResult's SearchHit
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
    @Indexed(boost = 1.0f)
    @Field String name

	/**
	 * known secondary names or synonyms
	 */
    @Field String[] synonyms = []

    /**
     * Type of the entity
     */
    @Field Type type

}
