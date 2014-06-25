package de.comprot.task

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.EntitySourceService
import org.quartz.InterruptableJob
import org.quartz.JobExecutionContext
import org.quartz.UnableToInterruptJobException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component class ProteinIndexingJob implements InterruptableJob {

    static def logger = LoggerFactory.getLogger ProteinIndexingJob

    @Autowired EntityIndexService indexService

    @Autowired EntitySourceService sourceService

    int pageSize = 50000

    private boolean interruptRequested = false

    @Override void execute(JobExecutionContext context) {
        logger.info 'started'
        indexService.deleteAll ComprotEntity.Type.PROTEIN

        def currentPageable = new PageRequest(0, pageSize)
        def currentPage = sourceService.getProteins currentPageable

        if (!currentPage.hasContent()) {
            logger.warn 'nothing to index'; return
        } else {
            indexService.save currentPage.content
        }

        while (currentPage.hasNext() && !interruptRequested) {
            currentPageable = currentPage.nextPageable()
            logger.info "indexing ${currentPageable.pageNumber}/${currentPage.totalPages}"
            currentPage = sourceService.getProteins currentPageable
            indexService.save currentPage.content
        }

        logger.info 'finished'
    }

    @Override void interrupt() {
        logger.info 'stop requested'
        interruptRequested = true
    }

}
