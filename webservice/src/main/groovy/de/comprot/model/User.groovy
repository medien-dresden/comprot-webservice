package de.comprot.model

import com.fasterxml.jackson.annotation.JsonIgnore
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

    static def ROLE_USER = 'ROLE_USER'

    @Pattern(
        message = 'can only contain letters and digits',
        regexp = '[\\w]*')
    @Length(min = 3, max = 100)
    @Id @NotNull String username

    @Pattern(
        message = 'should contain eight to twenty letters and numbers',
        regexp  = '^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$')
    @NotNull String password

    @JsonIgnore @NotNull String[] roles = ['ROLE_USER']

    @JsonIgnore @Override Collection<? extends GrantedAuthority> getAuthorities() {
        roles.collect({ new SimpleGrantedAuthority(it) })
    }

    @Override String getPassword() { password }
    @Override String getUsername() { username }

    // no extended account flags for the sake of simplicity

    @Override @JsonIgnore boolean isAccountNonExpired()     { true }
    @Override @JsonIgnore boolean isAccountNonLocked()      { true }
    @Override @JsonIgnore boolean isCredentialsNonExpired() { true }
    @Override @JsonIgnore boolean isEnabled()               { true }

}
