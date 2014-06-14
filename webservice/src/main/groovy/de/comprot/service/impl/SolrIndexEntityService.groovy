package de.comprot.service.impl

import de.comprot.model.IndexEntity
import de.comprot.repository.IndexEntityRepository
import de.comprot.service.IndexEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class SolrIndexEntityService implements IndexEntityService {

    @Autowired IndexEntityRepository comprotEntityRepository

    @Transactional
    @Override void addToIndex(IndexEntity entity) {
        comprotEntityRepository.save(entity)
    }

    @Transactional
    @Override void deleteFromIndex(Long id) {
        comprotEntityRepository.delete(id.toString())
    }

}
