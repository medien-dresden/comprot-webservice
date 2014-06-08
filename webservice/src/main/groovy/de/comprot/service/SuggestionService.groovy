package de.comprot.service

import de.comprot.model.Suggestion
import org.springframework.transaction.annotation.Transactional

public interface SuggestionService {

    @Transactional(readOnly = true)
    List<Suggestion> getSuggestions(String query)

}