package de.comprot.facade.v1.model

import de.comprot.core.model.BindingEntity
import org.springframework.hateoas.ResourceSupport

class BindingEntityDto extends ResourceSupport {

    @Delegate private BindingEntity delegate = new BindingEntity()

}
