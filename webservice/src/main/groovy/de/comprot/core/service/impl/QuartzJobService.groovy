package de.comprot.core.service.impl

import de.comprot.core.service.JobSchedulingException
import de.comprot.core.service.NoSuchEntityException
import de.comprot.core.model.JobEntity
import de.comprot.core.service.JobService
import org.quartz.Job
import org.quartz.Scheduler
import org.quartz.SchedulerException
import org.quartz.UnableToInterruptJobException
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

import java.beans.Introspector

import static org.quartz.JobBuilder.newJob
import static org.quartz.SimpleScheduleBuilder.simpleSchedule
import static org.quartz.TriggerBuilder.newTrigger

@Service class QuartzJobService implements JobService {

    @Autowired Scheduler scheduler

    @Autowired ApplicationContext context

    @Override void startJob(JobEntity job) {
        try {

            if (runningJobs.any { it.name == job.name })
                throw new JobSchedulingException("'${job.name}' is already running", null)

            scheduler.scheduleJob(
                    newJob((Class<? extends Job>) context.getType(job.name))
                            .build(),

                    newTrigger()
                            .withSchedule(simpleSchedule()
                                .withRepeatCount(0))

                            .startNow()
                            .build())

        } catch (NoSuchBeanDefinitionException exception) {
            throw new JobSchedulingException("'${job.name}' is not a valid job", exception)

        } catch (SchedulerException exception) {
            throw new JobSchedulingException("starting '${job.name}' failed", exception)
        }
    }

    @Override void stopJob(String id) {
        try {

            if (!runningJobs.any { it.id == id })
                throw new NoSuchEntityException()

            if (!scheduler.interrupt(id))
                throw new UnableToInterruptJobException('interrupt returned false')

        } catch (SchedulerException exception) {
            throw new JobSchedulingException("stopping '${id}' failed", exception)
        }
    }

    @Override JobEntity getJob(String id) {
        if (!runningJobs.any { id == it.id })
            throw new NoSuchEntityException()

        runningJobs.find { id == it.id }
    }

    @Override Set<JobEntity> getRunningJobs() {
        scheduler.currentlyExecutingJobs.collect({
            new JobEntity(
                    name: Introspector.decapitalize(it.jobDetail.jobClass.simpleName),
                    id: it.fireInstanceId,
                    since: it.fireTime
            )
        })
    }

}
