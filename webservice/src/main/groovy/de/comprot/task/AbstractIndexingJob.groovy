package de.comprot.task

import de.comprot.core.model.ComprotEntity
import org.quartz.InterruptableJob
import org.quartz.JobExecutionContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

abstract class AbstractIndexingJob implements InterruptableJob {

    int pageSize = 50000

    private Logger logger

    private boolean interruptRequested = false

    abstract Page<ComprotEntity> fetch(Pageable pageable)

    abstract clearIndex()

    abstract index(List entities)

    AbstractIndexingJob() {
        logger = LoggerFactory.getLogger(getClass())
    }

    @Override void execute(JobExecutionContext context) {
        logger.info 'started'
        clearIndex()

        def currentPageable = new PageRequest(0, pageSize)
        def currentPage = fetch currentPageable

        if (!currentPage.hasContent()) {
            logger.warn 'nothing to index'
            return
        } else {
            index currentPage.content
        }

        while (currentPage.hasNext() && !interruptRequested) {
            currentPageable = currentPage.nextPageable()

            logger.info "indexing ${ 100 * (currentPageable.pageNumber / currentPage.totalPages) as int }%"

            currentPage = fetch currentPageable
            index currentPage.content
        }

        logger.info 'finished'
    }

    @Override void interrupt() {
        logger.info 'stop requested'
        interruptRequested = true
    }

}
