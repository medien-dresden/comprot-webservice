package de.comprot.core.model

import de.comprot.core.model.validation.Password
import de.comprot.core.model.validation.Username
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Table(name = 'comprot_user')
@Entity class UserEntity implements UserDetails {

    @Id @NotNull @Username String username

    @Password String password

    @NotNull String[] roles = ['ROLE_USER']

    @Override Collection<? extends GrantedAuthority> getAuthorities() {
        roles.collect { new SimpleGrantedAuthority(it) }
    }

    @Override String getPassword() { password }
    @Override String getUsername() { username }

    // no extended account flags for the sake of simplicity

    @Override boolean isAccountNonExpired()     { true }
    @Override boolean isAccountNonLocked()      { true }
    @Override boolean isCredentialsNonExpired() { true }
    @Override boolean isEnabled()               { true }

}
