package de.comprot.core.model

import groovy.transform.EqualsAndHashCode
import org.hibernate.annotations.NaturalId
import org.hibernate.validator.constraints.Email
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.*
import javax.validation.constraints.NotNull

@Table(name = 'comprot_user', uniqueConstraints = [
        @UniqueConstraint(name = 'unique_username', columnNames = 'username'),
        @UniqueConstraint(name = 'unique_email', columnNames = 'email')
])
@EqualsAndHashCode(includeFields = true, includes = 'username')
@Entity class UserEntity implements UserDetails {

    @GeneratedValue @Id Long id

    @NotNull @NaturalId String username

    @NotNull @Email String email

    @NotNull String displayName

    @NotNull String password

    @NotNull String[] roles = ['ROLE_USER']

    @OrderColumn
    @OneToMany(mappedBy = 'user', cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotNull List<WorkbenchEntity> workbenches = []

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
