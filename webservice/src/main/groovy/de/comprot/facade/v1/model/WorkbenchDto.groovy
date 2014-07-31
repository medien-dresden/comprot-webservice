package de.comprot.facade.v1.model

import org.springframework.hateoas.ResourceSupport

class WorkbenchDto extends ResourceSupport {

    String label

    List<ComprotEntityDto> targets

    List<ComprotEntityDto> compounds

}
