package de.comprot.task.indexing

import de.comprot.core.model.BindingEntity
import de.comprot.core.service.BindingEntityIndexService
import de.comprot.core.service.BindingEntitySourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component class BindingIndexingJob extends IndexingJob<BindingEntity> {

    @Autowired BindingEntitySourceService sourceService

    @Autowired BindingEntityIndexService indexService

    @Override Page<BindingEntity> fetch(Pageable pageable) { sourceService.getBindings pageable }

    @Override def clearIndex() { indexService.deleteAll() }

    @Override def index(List entities) { indexService.save entities }

}
