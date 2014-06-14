package de.comprot.service

import de.comprot.model.IndexEntity

public interface IndexEntityService {

    public void addToIndex(IndexEntity entity)

    public void deleteFromIndex(Long id)

}