package de.comprot.service.impl

import de.comprot.model.Suggestion
import de.comprot.service.SuggestionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class DummySuggestionService implements SuggestionService {

    @Transactional(readOnly = true)
    @Override List<Suggestion> getSuggestions(String query) {
        [   // return some dummy data
            new Suggestion(label: query + ' Protein',  hits: query.length()),
            new Suggestion(label: query + ' Target',   hits: query.length() + 54),
            new Suggestion(label: query + ' Whatever', hits: query.length() + 12),
        ]
    }

}
