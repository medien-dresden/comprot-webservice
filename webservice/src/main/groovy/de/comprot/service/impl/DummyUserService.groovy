package de.comprot.service.impl

import de.comprot.data.UserDao
import de.comprot.model.User
import de.comprot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class DummyUserService implements UserService {

    @Autowired UserDao userDao;

    @Override void addUser(User user) {

    }

}
