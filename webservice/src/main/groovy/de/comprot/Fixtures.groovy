package de.comprot

import de.comprot.model.User
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.solr.core.SolrTemplate
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component class Fixtures {

    @Autowired SessionFactory sessionFactory

    @Autowired SolrTemplate solrTemplate

    @PostConstruct def initAdmin() {
        def session = sessionFactory.openSession()
        def currentAdmin = session.byId(User.class).load('admin')

        if (currentAdmin == null) {
            session.save(new User(
                    username: 'admin',
                    password: 'admin123',
                    roles: [ 'ROLE_ADMIN', 'ROLE_USER' ]
            ))
        }

        session.flush()
        session.close()
    }

}
