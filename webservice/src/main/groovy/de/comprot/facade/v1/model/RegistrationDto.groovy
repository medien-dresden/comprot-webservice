package de.comprot.facade.v1.model

import de.comprot.facade.v1.validation.Password
import de.comprot.facade.v1.validation.Username
import org.hibernate.validator.constraints.Email

import javax.validation.constraints.NotNull

class RegistrationDto {

    @Username @NotNull String username

    @Password @NotNull String password

    @NotNull @Email String email

    String displayName

}
