package de.comprot.persistence.impl

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired

class HibernateDao {

    @Autowired SessionFactory sessionFactory;

    def session() {
        sessionFactory.getCurrentSession()
    }

}
