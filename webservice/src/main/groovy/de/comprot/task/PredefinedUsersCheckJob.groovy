package de.comprot.task

import de.comprot.core.model.UserEntity
import de.comprot.core.service.NoSuchEntityException
import de.comprot.core.service.UserService
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component class PredefinedUsersCheckJob extends SecurityContextAwareJob {

    static def logger = LoggerFactory.getLogger PredefinedUsersCheckJob

    @Autowired UserService userService

    @Override def executeWithinSecurityContext(JobExecutionContext context) {
        try {
            userService.loadByUsername 'admin'
        } catch (NoSuchEntityException ignored) {
            logger.info 'creating admin user'
            userService.register new UserEntity(
                    username: 'admin',
                    password: 'admin123',
                    roles: ['ROLE_ADMIN', 'ROLE_USER'])
        }
    }

}
