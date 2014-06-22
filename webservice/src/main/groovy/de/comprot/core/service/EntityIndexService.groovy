package de.comprot.core.service

import de.comprot.core.model.ComprotEntity
import de.comprot.core.model.SuggestionEntity

interface EntityIndexService {

    void save(Collection<ComprotEntity> entities)

    void deleteAll(ComprotEntity.Type entityType)

    List<SuggestionEntity> getSuggestions(String query, int page, int size)

}