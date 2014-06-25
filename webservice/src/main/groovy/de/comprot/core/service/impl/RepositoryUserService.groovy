package de.comprot.core.service.impl

import de.comprot.core.service.NoSuchEntityException
import de.comprot.core.model.UserEntity
import de.comprot.core.repository.UserRepository
import de.comprot.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class RepositoryUserService implements UserService, UserDetailsService {

    @Autowired UserRepository repository

    @Transactional
    @Override void register(UserEntity user) { repository.persist user }

    @Transactional(readOnly = true)
    @Override UserEntity loadByUsername(String username) {
        def user = repository.findByUsername username

        if (user == null)
            throw new NoSuchEntityException()

        return user
    }

    // UserDetailsService
    @Transactional(readOnly = true)
    @Override UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = repository.findByUsername username

        if (user == null)
            throw new UsernameNotFoundException('no such user')

        return user
    }

}
