package de.comprot.security

import de.comprot.core.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class SecurityUserDetailsService implements UserDetailsService {

    @Autowired UserRepository repository

    @Transactional(readOnly = true)
    @Override UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = repository.findByUsername username
        if (!user) throw new UsernameNotFoundException('no such user')
        return user
    }

}
