package de.comprot.core.service.impl

import de.comprot.core.model.WorkbenchEntity
import de.comprot.core.repository.WorkbenchRepository
import de.comprot.core.service.ComprotEntityIndexService
import de.comprot.core.service.EntityPropertyConstraintException
import de.comprot.core.service.WorkbenchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class RepositoryWorkbenchService implements WorkbenchService {

    @Autowired WorkbenchRepository repository

    @Autowired ComprotEntityIndexService indexService

    @Transactional(readOnly = true)
    @Override WorkbenchEntity loadById(Long id) {
        repository.findById id
    }

    @Transactional
    @Override void save(WorkbenchEntity workbench) {
        if (workbench.compounds.any { it.isTargetRef() })
            throw new EntityPropertyConstraintException('compounds', 'collection contains targets')

        if (workbench.targets.any { it.isCompoundRef() })
            throw new EntityPropertyConstraintException('targets', 'collection contains compounds')

        reviseEntityPopularities workbench
        repository.persist workbench
    }

    def reviseEntityPopularities(WorkbenchEntity updatedWorkbench) {
        def deprecatedWorkbench = repository.findById updatedWorkbench.id

        def newList = updatedWorkbench.targets + updatedWorkbench.compounds
        def oldList = deprecatedWorkbench.targets + deprecatedWorkbench.compounds
        def entities = newList + oldList

        def removedEntities = entities.findAll {  oldList.contains(it) && !newList.contains(it) }
        def addedEntities   = entities.findAll { !oldList.contains(it) &&  newList.contains(it) }

        removedEntities.each { indexService.decreasePopularity it.id }
        addedEntities.each   { indexService.increasePopularity it.id }
    }

}
