package de.comprot.core.model

import javax.validation.constraints.Pattern

class JobEntity {

    @Pattern(regexp = '^\\w+$') String name

    String id

    Date since

}
