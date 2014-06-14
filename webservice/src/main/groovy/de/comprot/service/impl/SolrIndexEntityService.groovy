package de.comprot.service.impl

import de.comprot.model.IndexEntity
import de.comprot.repository.IndexEntityRepository
import de.comprot.service.IndexEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class SolrIndexEntityService implements IndexEntityService {

    @Autowired IndexEntityRepository indexEntityRepository

    @Override void addToIndex(IndexEntity entity) {
        indexEntityRepository.save(entity)
    }

    @Override void deleteFromIndex(Long id) {
        indexEntityRepository.delete(id.toString())
    }

}
