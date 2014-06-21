package de.comprot.task

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.EntitySourceService
import org.quartz.InterruptableJob
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.UnableToInterruptJobException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component class ProteinIndexingJob implements InterruptableJob {

    static def logger = LoggerFactory.getLogger(ProteinIndexingJob.class)

    @Autowired EntityIndexService indexService

    @Autowired EntitySourceService sourceService

    int pageSize = 50000

    private boolean interruptRequested = false

    @Override void execute(JobExecutionContext context) {
        logger.info('started')
        indexService.deleteAll(ComprotEntity.Type.PROTEIN)

        if (interruptRequested) { logger.info('stopped'); return }

        def firstPage = sourceService.getProteins(1, pageSize)
        def pagesAvailable = firstPage.pagesAvailable

        if (interruptRequested) { logger.info('stopped'); return }

        // add the first page to the index
        logger.info("indexing 1/$pagesAvailable")
        indexService.save(firstPage.items)

        if (interruptRequested) { logger.info('stopped'); return }

        // if more pages available, add them too
        if (pagesAvailable > 1) (2..pagesAvailable).each { pageNumber ->
            logger.info("indexing $pageNumber/$pagesAvailable")
            indexService.save(sourceService.getProteins(pageNumber, pageSize).items)
            if (interruptRequested) { logger.info('stopped'); return }
        }

        logger.info("finished")
    }

    @Override void interrupt() throws UnableToInterruptJobException {
        logger.info('stop requested')
        interruptRequested = true
    }

}
