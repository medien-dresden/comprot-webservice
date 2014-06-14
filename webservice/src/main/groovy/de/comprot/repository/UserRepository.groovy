package de.comprot.repository

import de.comprot.model.UserEntity

interface UserRepository {

    void persist(UserEntity user);

    UserEntity findByUsername(String username);

}
