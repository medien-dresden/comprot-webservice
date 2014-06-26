package de.comprot.task

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

abstract class SecurityContextAwareJob implements Job {

    @Override void execute(JobExecutionContext context) {
        if (SecurityContextHolder.context.authentication == null) {
            def securityContext = SecurityContextHolder.createEmptyContext()

            securityContext.authentication = new UsernamePasswordAuthenticationToken('system', null, [
                    new SimpleGrantedAuthority('ROLE_ADMIN')])

            SecurityContextHolder.context = securityContext
        }

        executeWithinSecurityContext(context)
    }

    abstract executeWithinSecurityContext(JobExecutionContext context)

}
