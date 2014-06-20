package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.util.Page

interface ComprotEntityRepository {

    Page<ComprotEntity> findAllProteins(int pageNumber, int pageSize)

}