package de.comprot.service

import de.comprot.model.ComprotEntity

public interface ComprotEntityService {

    public void addToIndex(ComprotEntity entity)

    public void deleteFromIndex(Long id)

}