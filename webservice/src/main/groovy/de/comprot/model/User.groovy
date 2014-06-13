package de.comprot.model

import com.fasterxml.jackson.annotation.JsonView
import org.hibernate.validator.constraints.Length
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Table(name = 'comprot_user')
@Entity class User implements UserDetails {

    static def ROLE_ADMIN = 'ROLE_ADMIN'
    static def ROLE_USER  = 'ROLE_USER'

    @Pattern(
        message = 'can only contain letters and digits',
        regexp = '[\\w]*')
    @Id @NotNull @Length(min = 3, max = 100)
    @JsonView(Views.Public.class) String username

    @Pattern(
        message = 'should contain eight to twenty letters and numbers',
        regexp  = '^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$')
    @JsonView(Views.Internal.class) @NotNull String password

    @JsonView(Views.Internal.class) @NotNull String[] roles = ['ROLE_USER']

    @Override @JsonView(Views.Internal.class)
    Collection<? extends GrantedAuthority> getAuthorities() {
        roles.collect({ new SimpleGrantedAuthority(it) })
    }

    @Override String getPassword() { password }
    @Override String getUsername() { username }

    // no extended account flags for the sake of simplicity

    @Override @JsonView(Views.Internal.class) boolean isAccountNonExpired()     { true }
    @Override @JsonView(Views.Internal.class) boolean isAccountNonLocked()      { true }
    @Override @JsonView(Views.Internal.class) boolean isCredentialsNonExpired() { true }
    @Override @JsonView(Views.Internal.class) boolean isEnabled()               { true }

}
