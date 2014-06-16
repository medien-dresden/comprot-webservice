package de.comprot.core.service

import de.comprot.core.model.SuggestionEntity

public interface SuggestionService {

    List<SuggestionEntity> getSuggestions(String query)

}