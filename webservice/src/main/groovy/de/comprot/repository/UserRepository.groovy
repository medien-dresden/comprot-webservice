package de.comprot.repository

import de.comprot.model.User

interface UserRepository {

    void persist(User user);

    User findByUsername(String username);

}
