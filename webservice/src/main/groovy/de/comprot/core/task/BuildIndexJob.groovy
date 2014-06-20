package de.comprot.core.task

import de.comprot.core.service.SearchIndexService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class BuildIndexJob implements Job {

    static def logger = LoggerFactory.getLogger(BuildIndexJob.class)

    @Autowired SearchIndexService indexService

    @Override void execute(JobExecutionContext context) {
        logger.info('indexing started')
    }

}
