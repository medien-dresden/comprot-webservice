package de.comprot.service

import de.comprot.model.User

import org.springframework.transaction.annotation.Transactional

interface UserService {

    @Transactional
    void addUser(User user);

}