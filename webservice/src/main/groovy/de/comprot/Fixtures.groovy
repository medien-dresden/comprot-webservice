package de.comprot

import de.comprot.model.UserEntity
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component class Fixtures {

    @Autowired SessionFactory sessionFactory

    @PostConstruct def initAdmin() {
        def session = sessionFactory.openSession()
        def currentAdmin = session.byId(UserEntity.class).load('admin')

        if (currentAdmin == null) {
            session.save(new UserEntity(
                    username: 'admin',
                    password: 'admin123',
                    roles: [ 'ROLE_ADMIN', 'ROLE_USER' ]
            ))
        }

        session.flush()
        session.close()
    }

}
