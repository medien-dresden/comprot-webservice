package de.comprot.core.repository.impl

import de.comprot.core.model.UserEntity
import de.comprot.core.repository.UserRepository
import org.springframework.orm.hibernate4.support.HibernateDaoSupport
import org.springframework.stereotype.Repository

@Repository class HibernateUserRepository extends HibernateDaoSupport implements UserRepository {

    @Override void persist(UserEntity user) { currentSession().save user }

    @Override UserEntity findByUsername(String username) {
        (UserEntity) currentSession()
                .byNaturalId(UserEntity)
                .using('username', username)
                .load()
    }

}
