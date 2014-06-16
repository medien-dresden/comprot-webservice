package de.comprot.core.task

import de.comprot.core.model.UserEntity
import de.comprot.core.repository.NoSuchUserException
import de.comprot.core.service.UserService
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class InitAdminUserJob implements Job {

    static def logger = LoggerFactory.getLogger(InitAdminUserJob.class)

    @Autowired UserService userService

    @Override void execute(JobExecutionContext context) {
        try {
            userService.loadByUsername('admin')
            logger.info('admin user exists')

        } catch (NoSuchUserException ignored) {
            logger.info('creating admin user')
            userService.register(new UserEntity(
                    username: 'admin',
                    password: 'admin123',
                    roles: ['ROLE_ADMIN', 'ROLE_USER']))
        }
    }
}
