package de.comprot.task.indexing

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.ComprotEntityIndexService
import de.comprot.core.service.ComprotEntitySourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component class DrugIndexingJob extends IndexingJob<ComprotEntity> {

    @Autowired ComprotEntitySourceService sourceService

    @Autowired ComprotEntityIndexService indexService

    @Override Page<ComprotEntity> fetch(Pageable pageable) { sourceService.getDrugs pageable }

    @Override def clearIndex() { indexService.deleteAll ComprotEntity.Type.DRUG }

    @Override def index(List entities) { indexService.save entities }

}
