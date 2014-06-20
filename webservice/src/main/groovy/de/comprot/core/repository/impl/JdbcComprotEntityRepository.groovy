package de.comprot.core.repository.impl

import de.comprot.core.repository.ComprotEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository class JdbcComprotEntityRepository implements ComprotEntityRepository {

    @Autowired NamedParameterJdbcTemplate template

    @Override Range<Long> getIdRange() {
        def range = template.queryForRowSet('SELECT MIN(ID) AS MIN_ID, MAX(ID) AS MAX_ID FROM TARGET', [:])
        range.first() // select first row
        range.getLong('MIN_ID')..range.getLong('MAX_ID')
    }

}
