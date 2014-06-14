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

    @Pattern(
        message = 'can only contain letters and digits',
        regexp = '[\\w]*')
    @Id @NotNull @Length(min = 3, max = 100)
    @JsonView(View.Public.class) String username

    @Pattern(
        message = 'should contain eight to twenty letters and numbers',
        regexp  = '^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$')
    @JsonView(View.Internal.class) @NotNull String password

    @JsonView(View.Internal.class) @NotNull String[] roles = ['ROLE_USER']

    @Override @JsonView(View.Internal.class)
    Collection<? extends GrantedAuthority> getAuthorities() {
        roles.collect({ new SimpleGrantedAuthority(it) })
    }

    @Override String getPassword() { password }
    @Override String getUsername() { username }

    // no extended account flags for the sake of simplicity

    @Override @JsonView(View.Internal.class) boolean isAccountNonExpired()     { true }
    @Override @JsonView(View.Internal.class) boolean isAccountNonLocked()      { true }
    @Override @JsonView(View.Internal.class) boolean isCredentialsNonExpired() { true }
    @Override @JsonView(View.Internal.class) boolean isEnabled()               { true }

}
