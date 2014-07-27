package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.BindingEntitySourceRepository
import de.comprot.core.service.BindingEntitySourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service class RepositoryBindingEntitySourceService implements BindingEntitySourceService {

    @Autowired BindingEntitySourceRepository repository

    @Override Page<ComprotEntity> getBindings(Pageable pageable) { repository.findAllBindings pageable }

}
