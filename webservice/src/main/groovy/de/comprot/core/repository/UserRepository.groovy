package de.comprot.core.repository

import de.comprot.core.model.UserEntity

interface UserRepository {

    void persist(UserEntity user);

    UserEntity findByUsername(String username);

}
