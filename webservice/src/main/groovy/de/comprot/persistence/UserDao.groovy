package de.comprot.persistence

import de.comprot.model.User

interface UserDao {

    void persist(User user);

    User findByUsername(String username);

}
