package de.comprot.service.impl

import de.comprot.repository.NoSuchUserException
import de.comprot.model.User
import de.comprot.repository.UserRepository
import de.comprot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class DefaultUserService implements UserService {

    @Autowired UserRepository userRepository

    @Transactional
    @Override void register(User user) {
        userRepository.persist(user)
    }

    @Transactional(readOnly = true)
    @Override User loadByUsername(String username) {
        def user = userRepository.findByUsername(username)

        if (user == null)
            throw new NoSuchUserException()

        return user
    }

}
