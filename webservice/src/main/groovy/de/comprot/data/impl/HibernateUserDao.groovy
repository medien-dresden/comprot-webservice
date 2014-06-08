package de.comprot.data.impl

import de.comprot.data.UserDao
import de.comprot.model.User
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository class HibernateUserDao implements UserDao {

    @Autowired SessionFactory sessionFactory;

    Session getSession() {
        sessionFactory.getCurrentSession()
    }

    @Override void addUser(User user) {
        getSession().save(user)
    }

}
