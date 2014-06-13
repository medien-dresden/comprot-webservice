package de.comprot.persistence.impl

import de.comprot.model.User
import de.comprot.persistence.UserDao
import org.springframework.stereotype.Repository

import javax.annotation.PostConstruct

@Repository class HibernateUserDao extends HibernateDao implements UserDao {

    @PostConstruct def initAdmin() {
        def session = sessionFactory.openSession()
        def transaction = session.beginTransaction()
        def currentAdmin = session.byId(User.class).load('admin')

        if (currentAdmin == null) {
            session.save(new User(
                    username: 'admin',
                    password: 'admin123',
                    roles: [ User.ROLE_ADMIN, User.ROLE_USER ]
            ))
        }

        session.flush()
        transaction.commit()
        session.close()
    }

    @Override void persist(User user) {
        session().save(user)
    }

    @Override User findByUsername(String username) {
        (User) session().byId(User.class).load(username)
    }

}
