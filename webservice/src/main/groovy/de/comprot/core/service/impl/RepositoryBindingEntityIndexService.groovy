package de.comprot.core.service.impl

import de.comprot.core.model.BindingEntity
import de.comprot.core.repository.BindingEntityIndexRepository
import de.comprot.core.service.BindingEntityIndexService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service class RepositoryBindingEntityIndexService implements BindingEntityIndexService {

    @Autowired BindingEntityIndexRepository repository

    @Override void save(Collection<BindingEntity> entities) { repository.save entities }

    @Override void deleteAll() { repository.deleteAll() }

    @Override Page<BindingEntity> getBindings(String id, Pageable pageable) {
        repository.findByEntity id, pageable
    }

}
