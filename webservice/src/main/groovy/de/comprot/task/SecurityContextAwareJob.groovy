package de.comprot.task

import de.comprot.core.model.UserEntity
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

abstract class SecurityContextAwareJob implements Job {

    @Override void execute(JobExecutionContext context) {
        if (!SecurityContextHolder.context.authentication) {
            def securityContext = SecurityContextHolder.createEmptyContext()
            def user = new UserEntity(username: 'system', roles: [ 'ROLE_ADMIN' ])

            securityContext.authentication = new UsernamePasswordAuthenticationToken(user, null, user.authorities)
            SecurityContextHolder.context = securityContext
        }

        executeWithinSecurityContext(context)
    }

    abstract executeWithinSecurityContext(JobExecutionContext context)

}
