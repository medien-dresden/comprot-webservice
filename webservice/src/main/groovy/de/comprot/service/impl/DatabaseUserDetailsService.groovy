package de.comprot.service.impl

import de.comprot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component('userDetailsService') class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired UserRepository userDao

    @Transactional(readOnly = true)
    @Override UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = userDao.findByUsername(username)

        if (user == null)
            throw new UsernameNotFoundException('no such user')

        return user
    }

}
