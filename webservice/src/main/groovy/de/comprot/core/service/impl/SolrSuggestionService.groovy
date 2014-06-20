package de.comprot.core.service.impl

import de.comprot.core.model.SuggestionEntity
import de.comprot.core.repository.ComprotEntityIndexRepository
import de.comprot.core.service.SuggestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class SolrSuggestionService implements SuggestionService {

    @Autowired ComprotEntityIndexRepository indexEntityRepository

    @Override List<SuggestionEntity> getSuggestions(String query) {
        [   // return some dummy data
            new SuggestionEntity(label: query + ' Protein',  hits: query.length()),
            new SuggestionEntity(label: query + ' Target',   hits: query.length() + 54),
            new SuggestionEntity(label: query + ' Whatever', hits: query.length() + 12),
        ]
    }

}
