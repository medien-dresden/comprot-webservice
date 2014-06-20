package de.comprot.task

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.EntityIndexService
import de.comprot.core.service.EntitySourceService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ProteinIndexingJob implements Job {

    static def logger = LoggerFactory.getLogger(ProteinIndexingJob.class)

    @Autowired EntityIndexService indexService

    @Autowired EntitySourceService sourceService

    int pageSize = 100000

    @Override void execute(JobExecutionContext context) {
        logger.info('indexing started')
        indexService.deleteAll(ComprotEntity.Type.PROTEIN)

        def firstPage = sourceService.getProteins(1, pageSize)
        def pagesAvailable = firstPage.pagesAvailable

        // add the first page to the index
        indexService.save(firstPage.items)

        // if more pages available, add them too
        if (pagesAvailable > 1) (2..pagesAvailable).each { pageNumber ->
            logger.info("indexing $pageNumber/$pagesAvailable")
            indexService.save(sourceService.getProteins(pageNumber, pageSize).items)
        }

        logger.info("indexing finished")
    }

}
