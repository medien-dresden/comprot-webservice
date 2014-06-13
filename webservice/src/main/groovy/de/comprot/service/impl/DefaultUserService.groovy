package de.comprot.service.impl

import de.comprot.UsernameAlreadyTakenException
import de.comprot.model.User
import de.comprot.persistence.UserDao
import de.comprot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service('userService') class DefaultUserService implements UserService, UserDetailsService {

    @Autowired UserDao userDao

    @Transactional
    @Override void register(User user) throws UsernameAlreadyTakenException {
        try {
            userDao.persist(user)
        } catch (DataIntegrityViolationException exception) {
            throw new UsernameAlreadyTakenException(user.getUsername(), exception)
        }
    }

    @Transactional(readOnly = true)
    @Override User loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = userDao.findByUsername(username)

        if (user == null)
            throw new UsernameNotFoundException('no such user')

        return user
    }

}
