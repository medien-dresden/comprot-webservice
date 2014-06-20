package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity

interface EntityIndexDeletionRepository {

    void deleteAll(ComprotEntity.Type entityType)

}