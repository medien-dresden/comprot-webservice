package de.comprot.core.repository.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.EntityIndexDeletionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.solr.core.SolrTemplate
import org.springframework.data.solr.core.query.Criteria
import org.springframework.data.solr.core.query.SimpleQuery
import org.springframework.stereotype.Repository

@Repository class EntityIndexRepositoryImpl implements EntityIndexDeletionRepository {

    @Autowired SolrTemplate template

    @Override void deleteAll(ComprotEntity.Type entityType) {
        template.delete(new SimpleQuery(new Criteria(ComprotEntity.FIELD_TYPE).is(entityType)))
        template.commit()
    }

}
