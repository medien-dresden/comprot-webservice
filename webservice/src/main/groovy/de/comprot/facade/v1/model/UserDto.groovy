package de.comprot.facade.v1.model

import org.springframework.hateoas.ResourceSupport

class UserDto extends ResourceSupport {

    String username

    String email

    String displayName

    String[] roles

}
