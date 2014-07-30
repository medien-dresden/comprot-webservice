package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.ComprotEntitySourceRepository
import de.comprot.core.service.ComprotEntitySourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service class RepositoryComprotEntitySourceService implements ComprotEntitySourceService {

    @Autowired ComprotEntitySourceRepository repository

    @Override Page<ComprotEntity> getTargets(Pageable pageable) { repository.findAllTargets pageable }

    @Override Page<ComprotEntity> getCompounds(Pageable pageable) { repository.findAllCompounds pageable }

}
