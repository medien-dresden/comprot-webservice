package de.comprot.facade.v1.controller

import de.comprot.core.model.JobEntity
import de.comprot.core.service.JobService
import de.comprot.core.service.MappingService
import de.comprot.facade.Version
import de.comprot.facade.v1.model.JobDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RequestMapping('api/jobs')
@RestController class JobController {

    @Autowired JobService jobService

    @Autowired MappingService mappingService

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = Version.V1)
    def post(@Valid @RequestBody JobDto job) { jobService.startJob mappingService.generate(job, JobEntity) }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{id}', method = RequestMethod.DELETE)
    def delete(@PathVariable('id') String id) { jobService.stopJob id }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = '{id}', method = RequestMethod.GET, produces = Version.V1)
    def get(@PathVariable('id') String id) { mappingService.generate jobService.getJob(id), JobDto }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = Version.V1)
    def getAll() { mappingService.transferSet jobService.runningJobs, JobDto }

}
