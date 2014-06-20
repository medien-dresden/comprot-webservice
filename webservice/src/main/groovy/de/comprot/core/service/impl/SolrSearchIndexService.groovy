package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.ComprotEntityRepository
import de.comprot.core.service.SearchIndexService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class SolrSearchIndexService implements SearchIndexService {

    @Autowired ComprotEntityRepository comprotEntityRepository

    @Override void addToIndex(ComprotEntity entity) {
        comprotEntityRepository.save(entity)
    }

    @Override void deleteFromIndex(Long id) {
        comprotEntityRepository.delete(id.toString())
    }

    @Override void getComprotEntityCount() {
        comprotEntityRepository.count()
    }

}
