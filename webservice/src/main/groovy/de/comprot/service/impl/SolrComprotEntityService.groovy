package de.comprot.service.impl

import de.comprot.model.ComprotEntity
import de.comprot.repository.ComprotEntityRepository
import de.comprot.service.ComprotEntityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class SolrComprotEntityService implements ComprotEntityService {

    @Autowired ComprotEntityRepository comprotEntityRepository

    @Transactional
    @Override void addToIndex(ComprotEntity entity) {
        comprotEntityRepository.save(entity)
    }

    @Transactional
    @Override void deleteFromIndex(Long id) {
        comprotEntityRepository.delete(id.toString())
    }

}
