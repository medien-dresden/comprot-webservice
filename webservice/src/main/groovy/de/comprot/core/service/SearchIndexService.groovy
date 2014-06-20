package de.comprot.core.service

import de.comprot.core.model.ComprotEntity

interface SearchIndexService {

    void addToIndex(ComprotEntity entity)

    void deleteFromIndex(Long id)

    void getComprotEntityCount();

}