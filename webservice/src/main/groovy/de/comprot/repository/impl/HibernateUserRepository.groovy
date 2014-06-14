package de.comprot.repository.impl

import de.comprot.model.User
import de.comprot.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository class HibernateUserRepository extends HibernateRepository implements UserRepository {

    @Override void persist(User user) {
        session().save(user)
    }

    @Override User findByUsername(String username) {
        (User) session().byId(User.class).load(username)
    }

}
