package de.comprot.task

import de.comprot.core.repository.ComprotEntityRepository
import de.comprot.core.service.SearchIndexService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class BuildProteinIndexJob implements Job {

    static def logger = LoggerFactory.getLogger(BuildProteinIndexJob.class)

    @Autowired SearchIndexService indexService

    @Autowired ComprotEntityRepository comprotRepository

    int pageSize = 10000

    @Override void execute(JobExecutionContext context) {
        if (indexService.hasEmptyIndex()) { // FIXME do with specialized task trigger
            logger.info('indexing started')

            def firstPage = comprotRepository.findAllProteins(1, pageSize)
            def pagesAvailable = firstPage.pagesAvailable

            // add all items of the first page to the index
            firstPage.items.each { indexService.addToIndex(it) }

            // if more pages are available, add all the others too
            if (pagesAvailable > 1) (2..pagesAvailable).each { pageNumber ->
                logger.info("indexing $pageNumber/$pagesAvailable")

                comprotRepository.findAllProteins(pageNumber, pageSize).items.each { entity ->
                    indexService.addToIndex(entity)
                }
            }

            logger.info("indexing finished (${pagesAvailable * pageSize} proteins added)")

        } else {
            logger.info('index already populated')
        }
    }
}
