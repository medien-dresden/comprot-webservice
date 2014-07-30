package de.comprot.core.repository

import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ComprotEntitySourceRepository {

    Page<ComprotEntity> findAllTargets(Pageable pageable)

    Page<ComprotEntity> findAllCompounds(Pageable pageable)

}