package de.comprot.service

import de.comprot.model.Suggestion

public interface SuggestionService {

    List<Suggestion> getSuggestions(String query)

}