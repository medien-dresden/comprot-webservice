package de.comprot.facade.v1.model

import de.comprot.core.model.JobEntity

import javax.validation.constraints.Pattern

class JobDto extends JobEntity {

    @Pattern(regexp = '^\\w+$') String name

}
