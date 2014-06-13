package de.comprot.persistence.impl

import de.comprot.model.User
import de.comprot.persistence.UserDao
import org.springframework.stereotype.Repository

import javax.annotation.PostConstruct

@Repository class HibernateUserDao extends HibernateDao implements UserDao {

    @Override void persist(User user) {
        session().save(user)
    }

    @Override User findByUsername(String username) {
        (User) session().byId(User.class).load(username)
    }

}
