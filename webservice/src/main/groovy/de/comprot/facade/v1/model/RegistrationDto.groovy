package de.comprot.facade.v1.model

import de.comprot.facade.v1.validation.Password
import de.comprot.facade.v1.validation.Username

import javax.validation.constraints.NotNull

class RegistrationDto {

    @Username @NotNull String username

    @Password @NotNull String password

}
