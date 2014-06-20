package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import de.comprot.common.Page

interface EntitySourceRepository {

    Page<ComprotEntity> findAllProteins(int pageNumber, int pageSize)

}