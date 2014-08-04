package de.comprot.core.repository.impl

import de.comprot.core.repository.ComprotEntityUpdateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.solr.core.SolrTemplate
import org.springframework.data.solr.core.query.PartialUpdate
import org.springframework.stereotype.Repository

@Repository class ComprotEntityIndexRepositoryImpl implements ComprotEntityUpdateRepository {

    @Autowired SolrTemplate template

    @Override void updatePopularity(String id, Long popularity) {
        def update = new PartialUpdate('id', id)

        update.add 'popularity', popularity

        template.setSolrCore 'comprotentity'
        // template.saveBean update
        // template.commit()
    }

}
