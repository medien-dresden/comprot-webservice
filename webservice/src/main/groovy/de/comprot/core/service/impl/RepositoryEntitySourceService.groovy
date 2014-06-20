package de.comprot.core.service.impl

import de.comprot.common.Page
import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.EntitySourceRepository
import de.comprot.core.service.EntitySourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class RepositoryEntitySourceService implements EntitySourceService {

    @Autowired EntitySourceRepository repository

    @Override Page<ComprotEntity> getProteins(int pageNumber, int pageSize) {
        repository.findAllProteins(pageNumber, pageSize)
    }

}
