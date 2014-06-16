package de.comprot.core.service

import de.comprot.core.model.UserEntity

interface UserService {

    void register(UserEntity user)

    UserEntity loadByUsername(String username)

}