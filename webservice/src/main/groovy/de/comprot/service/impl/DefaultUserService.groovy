package de.comprot.service.impl

import de.comprot.model.UserEntity
import de.comprot.repository.NoSuchUserException
import de.comprot.repository.UserRepository
import de.comprot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class DefaultUserService implements UserService, UserDetailsService {

    @Autowired UserRepository userRepository

    @Transactional
    @Override void register(UserEntity user) {
        userRepository.persist(user)
    }

    @Transactional(readOnly = true)
    @Override UserEntity loadByUsername(String username) {
        def user = userRepository.findByUsername(username)

        if (user == null)
            throw new NoSuchUserException()

        return user
    }

    // UserDetailsService
    @Transactional(readOnly = true)
    @Override UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = userRepository.findByUsername(username)

        if (user == null)
            throw new UsernameNotFoundException('no such user')

        return user
    }

}
