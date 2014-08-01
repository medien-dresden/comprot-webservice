package de.comprot.facade.v1.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.hateoas.ResourceSupport

import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
class WorkbenchDto extends ResourceSupport {

    @NotEmpty String label

    @NotNull List<ComprotEntityDto> targets

    @NotNull List<ComprotEntityDto> compounds

}
