package de.comprot.service

import de.comprot.UsernameAlreadyTakenException
import de.comprot.model.User

interface UserService {

    void register(User user) throws UsernameAlreadyTakenException

}