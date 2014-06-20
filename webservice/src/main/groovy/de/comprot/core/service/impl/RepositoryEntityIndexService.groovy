package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.model.SuggestionEntity
import de.comprot.core.repository.EntityIndexRepository
import de.comprot.core.service.EntityIndexService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class RepositoryEntityIndexService implements EntityIndexService {

    @Autowired EntityIndexRepository repository

    @Override void save(Collection<ComprotEntity> entities) {
        repository.save(entities)
    }

    @Override void deleteAll(ComprotEntity.Type entityType) {
        repository.deleteAll(entityType)
    }

    @Override List<SuggestionEntity> getSuggestions(String query) {
        [   // return some dummy data
            new SuggestionEntity(label: query + ' Protein',  hits: query.length()),
            new SuggestionEntity(label: query + ' Target',   hits: query.length() + 54),
            new SuggestionEntity(label: query + ' Whatever', hits: query.length() + 12),
        ]
    }

}
