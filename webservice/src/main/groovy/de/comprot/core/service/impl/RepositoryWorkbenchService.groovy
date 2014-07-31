package de.comprot.core.service.impl

import de.comprot.core.model.WorkbenchEntity
import de.comprot.core.repository.WorkbenchRepository
import de.comprot.core.service.WorkbenchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class RepositoryWorkbenchService implements WorkbenchService {

    @Autowired WorkbenchRepository repository

    @Transactional(readOnly = true)
    @Override WorkbenchEntity loadById(Long id) {
        repository.findById id
    }

}
