package de.comprot.core.service

import de.comprot.core.model.IndexEntity

public interface IndexEntityService {

    public void addToIndex(IndexEntity entity)

    public void deleteFromIndex(Long id)

}