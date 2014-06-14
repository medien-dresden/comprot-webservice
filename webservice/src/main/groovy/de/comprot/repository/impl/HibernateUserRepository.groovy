package de.comprot.repository.impl

import de.comprot.model.UserEntity
import de.comprot.repository.UserRepository
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate4.support.HibernateDaoSupport
import org.springframework.stereotype.Repository

@Repository class HibernateUserRepository extends HibernateDaoSupport implements UserRepository {

    @Override void persist(UserEntity user) {
        currentSession().save(user)
    }

    @Override UserEntity findByUsername(String username) {
        (UserEntity) currentSession().byId(UserEntity.class).load(username)
    }

}
