package de.comprot.core.service.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.model.UserEntity
import de.comprot.core.model.WorkbenchEntity
import de.comprot.core.repository.UserRepository
import de.comprot.core.service.EntityPropertyConstraintException
import de.comprot.core.service.NoSuchEntityException
import de.comprot.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class RepositoryUserService implements UserService, UserDetailsService {

    @Autowired UserRepository repository

    @Autowired PasswordEncoder passwordEncoder

    @Value('${users.predefined}') String[] predefinedUsers = []

    @Transactional
    @Override void register(UserEntity user) {
        if (predefinedUsers.contains(user.username))
            throw new EntityPropertyConstraintException('username', 'is not allowed')

        if (user.displayName == null)
            user.displayName = user.username

        user.password = passwordEncoder.encode user?.password
        user.email = user.email?.toLowerCase()

        if (user.workbenches.isEmpty()) {
            user.workbenches.add new WorkbenchEntity(user: user,
                    targets: [ new ComprotEntity(comprotId: 703, type: ComprotEntity.Type.COMPOUND) ]) // XXX
        }

        repository.persist user
    }

    @Transactional(readOnly = true)
    @Override UserEntity loadByUsername(String username) {
        if (username == 'me') {
            username = SecurityContextHolder.context?.authentication?.name
        }

        def user = repository.findByUsername username
        if (!user) throw new NoSuchEntityException()
        return user
    }

    // UserDetailsService
    @Transactional(readOnly = true)
    @Override UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = repository.findByUsername username
        if (!user) throw new UsernameNotFoundException('no such user')
        return user
    }

}
