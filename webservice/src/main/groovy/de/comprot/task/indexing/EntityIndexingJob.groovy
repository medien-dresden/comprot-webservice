package de.comprot.task.indexing

import de.comprot.core.service.EntityIndexService
import org.springframework.beans.factory.annotation.Autowired

abstract class EntityIndexingJob extends IndexingJob {

    @Autowired EntityIndexService indexService

    @Override def index(List entities) {
        indexService.save entities
    }

}
