package de.comprot.core.service

import de.comprot.core.model.JobEntity
import org.springframework.security.access.prepost.PreAuthorize

interface JobService {

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void startJob(JobEntity job)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    void stopJob(String id)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    JobEntity getJob(String id)

    @PreAuthorize('hasRole("ROLE_ADMIN")')
    Set<JobEntity> getRunningJobs()

}