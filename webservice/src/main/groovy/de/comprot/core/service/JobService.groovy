package de.comprot.core.service

import de.comprot.core.model.JobEntity

interface JobService {

    void startJob(JobEntity job)

    void stopJob(String id)

    JobEntity getJob(String id)

    Set<JobEntity> getRunningJobs()

}