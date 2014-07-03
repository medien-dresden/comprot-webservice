package de.comprot.facade.v1.model

import de.comprot.core.model.ComprotEntity
import org.springframework.hateoas.ResourceSupport

class EntityDto extends ResourceSupport {

    @Delegate private ComprotEntity delegate = new ComprotEntity()

}
