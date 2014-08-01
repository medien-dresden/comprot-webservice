package de.comprot.facade.v1.model

import org.springframework.hateoas.ResourceSupport

class BindingEntityDto extends ResourceSupport {

    String interaction

    ComprotEntityDto compound

    ComprotEntityDto target

}
