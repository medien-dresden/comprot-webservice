package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.model.SuggestionEntity
import de.comprot.core.repository.EntityIndexRepository
import de.comprot.core.service.EntityIndexService
import de.comprot.facade.v1.model.SuggestionDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service class RepositoryEntityIndexService implements EntityIndexService {

    @Autowired EntityIndexRepository repository

    @Override void save(Collection<ComprotEntity> entities) {
        repository.save entities
    }

    @Override void deleteAll(ComprotEntity.Type entityType) {
        repository.deleteByType entityType
    }

    @Override List<SuggestionEntity> getSuggestions(String query, int page, int size) {
        def result = []

        repository.findSuggestions(query, new PageRequest(page, size)).facetResultPages.each {
            it.content.each {
                result << new SuggestionEntity(label: it.value, hits: it.valueCount)
            }
        }

        return result
    }

}
