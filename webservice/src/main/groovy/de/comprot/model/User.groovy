package de.comprot.model

import org.hibernate.validator.constraints.Length

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Pattern

@Entity
class User {

    @Pattern(regexp = '[\\w]*', message = 'can only contain letters and digits')
    @Length(min = 3, max = 100)
    @Id String userName

}
