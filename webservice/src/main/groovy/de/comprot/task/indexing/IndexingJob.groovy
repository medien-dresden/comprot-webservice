package de.comprot.task.indexing

import de.comprot.core.model.ComprotEntity
import de.comprot.task.SecurityContextAwareJob
import org.quartz.InterruptableJob
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

abstract class IndexingJob extends SecurityContextAwareJob implements InterruptableJob {

    @Value('${indexing.pageSize}') int pageSize

    private Logger logger

    private boolean interruptRequested = false

    abstract Page<ComprotEntity> fetch(Pageable pageable)

    abstract clearIndex()

    abstract index(List entities)

    IndexingJob() { logger = LoggerFactory.getLogger(getClass()) }

    @Override def executeWithinSecurityContext(JobExecutionContext context) {
        logger.info "started with $pageSize entities per page"
        clearIndex()

        def currentPageable = new PageRequest(0, pageSize)
        def currentPage = fetch currentPageable

        if (!currentPage.hasContent()) {
            logger.warn 'source is empty'
            return
        } else {
            index currentPage.content
        }

        while (currentPage.hasNext() && !interruptRequested) {
            currentPageable = currentPage.nextPageable()

            logger.info "${ 100 * (currentPageable.pageNumber / currentPage.totalPages) as int }%"

            currentPage = fetch currentPageable
            index currentPage.content
        }

        logger.info 'finished'
    }

    @Override void interrupt() {
        logger.info 'stopping'
        interruptRequested = true
    }

}
