package de.comprot.core.task

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CreateAppDatabaseJob implements Job {

    @Override void execute(JobExecutionContext context) {
        // TODO
    }

}
