package de.comprot.core.service.impl

import de.comprot.core.model.IndexEntity
import de.comprot.core.repository.IndexEntityRepository
import de.comprot.core.service.IndexEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class SolrIndexEntityService implements IndexEntityService {

    @Autowired IndexEntityRepository indexEntityRepository

    @Override void addToIndex(IndexEntity entity) {
        indexEntityRepository.save(entity)
    }

    @Override void deleteFromIndex(Long id) {
        indexEntityRepository.delete(id.toString())
    }

}
