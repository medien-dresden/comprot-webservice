package de.comprot.service

import de.comprot.model.User

interface UserService {

    void register(User user)

    User loadByUsername(String username)

}