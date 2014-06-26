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
        initAdminUser()
        initAnonymousUser()
        initSystemUser()
    }

    def initAdminUser() {
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

    def initAnonymousUser() {
        try {
            userService.loadByUsername 'anonymous'
        } catch (NoSuchEntityException ignored) {
            logger.info 'creating anonymous user'
            userService.register new UserEntity(
                    username: 'anonymous',
                    password: 'anonymous123',
                    isEnabled: false,
                    roles: [])
        }
    }

    def initSystemUser() {
        try {
            userService.loadByUsername 'system'
        } catch (NoSuchEntityException ignored) {
            logger.info 'creating system user'
            userService.register new UserEntity(
                    username: 'system',
                    password: 'system123',
                    isEnabled: false,
                    roles: [])
        }
    }

}
