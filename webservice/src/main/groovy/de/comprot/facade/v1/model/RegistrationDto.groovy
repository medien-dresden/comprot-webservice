package de.comprot.facade.v1.model

import de.comprot.core.model.validation.Password
import de.comprot.core.model.validation.Username

import javax.validation.constraints.NotNull

class RegistrationDto {

    @Username @NotNull String username

    @Password @NotNull String password

}
