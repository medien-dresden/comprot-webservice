package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.model.SuggestionEntity
import de.comprot.core.repository.BindingEntityIndexRepository
import de.comprot.core.repository.ComprotEntityIndexRepository
import de.comprot.core.service.ComprotEntityIndexService
import de.comprot.core.service.MappingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class RepositoryComprotEntityIndexService implements ComprotEntityIndexService {

    @Autowired ComprotEntityIndexRepository repository

    @Autowired MappingService mappingService

    @Override void save(Collection<ComprotEntity> entities) {
        repository.save entities
    }

    @Override void deleteAll(ComprotEntity.Type entityType) {
        repository.deleteByType entityType
    }

    @Override List<SuggestionEntity> getSuggestions(String query, int limit) {
        repository.findSuggestions(query, new PageRequest(0, limit)).facetResultPages
                .collectNested { mappingService.generateList(it.content as List, SuggestionEntity) }.flatten()
    }

    @Override Page<ComprotEntity> search(String query, Pageable pageable) {
        repository.findByQuery query.replace(' ', '\\ '), pageable
    }

    @Override ComprotEntity getEntity(String id) {
        repository.findById id
    }

    // FIXME just a hack due to atomic update issues
    @Transactional @Override void increasePopularity(String id) {
        def entity = repository.findById id
        entity.popularity++

        repository.deleteById id
        repository.save entity
    }

    // FIXME just a hack due to atomic update issues
    @Transactional @Override void decreasePopularity(String id) {
        def entity = repository.findById id
        entity.popularity--

        repository.deleteById id
        repository.save entity
    }

}
