package de.comprot.task

import de.comprot.common.NoSuchEntityException
import de.comprot.core.model.UserEntity
import de.comprot.core.service.UserService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component class AdminUserCheckJob implements Job {

    static def logger = LoggerFactory.getLogger(AdminUserCheckJob.class)

    @Autowired UserService userService

    @Override void execute(JobExecutionContext context) {
        try {
            userService.loadByUsername('admin')
            logger.info('admin user exists')

        } catch (NoSuchEntityException ignored) {
            logger.info('creating admin user')
            userService.register(new UserEntity(
                    username: 'admin',
                    password: 'admin123',
                    roles: ['ROLE_ADMIN', 'ROLE_USER']))
        }
    }
}
