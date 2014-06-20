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

    @Override void execute(JobExecutionContext context) {
        logger.info('indexing started')
        logger.info('COMPROT PROTEIN ID RANGE: ' + comprotRepository.getIdRange().toString())
    }

}
