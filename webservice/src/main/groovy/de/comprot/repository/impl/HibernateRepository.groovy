package de.comprot.repository.impl

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired

class HibernateRepository {

    @Autowired SessionFactory sessionFactory;

    def session() {
        sessionFactory.getCurrentSession()
    }

}
