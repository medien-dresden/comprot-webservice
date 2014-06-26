package de.comprot.core.service.impl

import de.comprot.core.service.NoSuchEntityException
import de.comprot.core.model.UserEntity
import de.comprot.core.repository.UserRepository
import de.comprot.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class RepositoryUserService implements UserService {

    @Autowired UserRepository repository

    @Autowired PasswordEncoder passwordEncoder

    @Value('${users.predefined}') String[] predefinedUsers = []

    @Transactional
    @Override void register(UserEntity user) {
        if (predefinedUsers.contains(user.username))
            throw new DataIntegrityViolationException('username is not allowed')

        user.password = passwordEncoder.encode user.password
        repository.persist user
    }

    @Transactional(readOnly = true)
    @Override UserEntity loadByUsername(String username) {
        if (username == 'me') {
            username = SecurityContextHolder.context?.authentication?.name
        }

        def user = repository.findByUsername username

        if (user == null)
            throw new NoSuchEntityException()

        return user
    }

}
