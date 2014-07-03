package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.model.SuggestionEntity
import de.comprot.core.repository.EntityIndexRepository
import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.MappingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service class RepositoryEntityIndexService implements EntityIndexService {

    @Autowired EntityIndexRepository repository

    @Autowired MappingService mappingService

    @Override void save(Collection<ComprotEntity> entities) {
        repository.save entities
    }

    @Override void deleteAll(ComprotEntity.Type entityType) {
        repository.deleteByType entityType
    }

    @Override List<SuggestionEntity> getSuggestions(String query, int limit) {
        repository.findSuggestions(query, new PageRequest(0, limit)).facetResultPages
                .collectNested { mappingService.generate(it.content, SuggestionEntity) }.flatten()
    }

    @Override Page<ComprotEntity> search(String query, int page, int size) {
        repository.findByQuery query, new PageRequest(page, size)
    }

}
