package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.ComprotEntityIndexRepository
import de.comprot.core.service.SearchIndexService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class SolrSearchIndexService implements SearchIndexService {

    @Autowired ComprotEntityIndexRepository comprotEntityRepository

    @Override boolean hasEmptyIndex() {
        comprotEntityRepository.count() == 0
    }

    @Override void addToIndex(ComprotEntity entity) {
        // this might not be the best way
        entity.setId(entity.hashCode())
        comprotEntityRepository.save(entity)
    }

}
