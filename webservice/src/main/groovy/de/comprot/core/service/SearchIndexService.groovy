package de.comprot.core.service

import de.comprot.core.model.ComprotEntity

interface SearchIndexService {

    boolean hasEmptyIndex()

    void addToIndex(ComprotEntity entity)

}