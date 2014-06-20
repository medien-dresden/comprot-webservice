package de.comprot.core.service

import de.comprot.core.model.ComprotEntity
import de.comprot.common.Page

interface EntitySourceService {

    Page<ComprotEntity> getProteins(int pageNumber, int pageSize)

}