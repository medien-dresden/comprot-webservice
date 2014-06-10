package de.comprot.service.impl

import de.comprot.persistence.UserDao
import de.comprot.model.User
import de.comprot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service class DummyUserService implements UserService {

    @Autowired UserDao userDao;

    @Transactional
    @Override void addUser(User user) {

    }

}
