package de.comprot.facade.v1.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.hateoas.ResourceSupport

@JsonIgnoreProperties(ignoreUnknown = true)
class ComprotEntityDto extends ResourceSupport {

    String name

    String type

    String[] synonyms

    Long taxonomyId

    String sourceId

}
