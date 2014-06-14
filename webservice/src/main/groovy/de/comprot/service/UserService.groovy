package de.comprot.service

import de.comprot.model.UserEntity

interface UserService {

    void register(UserEntity user)

    UserEntity loadByUsername(String username)

}