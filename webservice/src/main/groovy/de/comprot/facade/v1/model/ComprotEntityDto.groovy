package de.comprot.facade.v1.model

import org.springframework.hateoas.ResourceSupport

class ComprotEntityDto extends ResourceSupport {

    String name

    String type

    String[] synonyms

    Long taxonomyId

    String sourceId

}
