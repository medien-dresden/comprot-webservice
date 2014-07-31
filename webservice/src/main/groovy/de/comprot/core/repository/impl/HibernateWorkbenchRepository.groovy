package de.comprot.core.repository.impl

import de.comprot.core.model.WorkbenchEntity
import de.comprot.core.repository.WorkbenchRepository
import org.springframework.orm.hibernate4.support.HibernateDaoSupport
import org.springframework.stereotype.Repository

@Repository class HibernateWorkbenchRepository extends HibernateDaoSupport implements WorkbenchRepository {

    @Override void persist(WorkbenchEntity workbench) { currentSession().saveOrUpdate workbench }

    @Override WorkbenchEntity findById(Long id) {
        (WorkbenchEntity) currentSession().byId(WorkbenchEntity).load(id)
    }

}
