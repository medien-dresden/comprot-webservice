package de.comprot.core.repository

import de.comprot.core.model.WorkbenchEntity

interface WorkbenchRepository {

    void persist(WorkbenchEntity workbench)

    WorkbenchEntity findById(Long id)

}
