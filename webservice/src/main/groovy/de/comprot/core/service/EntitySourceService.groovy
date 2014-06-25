package de.comprot.core.service

import de.comprot.core.model.ComprotEntity
import de.comprot.common.Page
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface EntitySourceService {

    Page<ComprotEntity> getProteins(Pageable pageable)

    Page<ComprotEntity> getDrugs(Pageable pageable)

}