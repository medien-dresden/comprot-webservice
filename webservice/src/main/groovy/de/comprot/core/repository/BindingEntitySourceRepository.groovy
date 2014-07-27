package de.comprot.core.repository

import de.comprot.core.model.BindingEntity
import de.comprot.core.model.ComprotEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BindingEntitySourceRepository {

    Page<BindingEntity> findAllBindings(Pageable pageable)

}