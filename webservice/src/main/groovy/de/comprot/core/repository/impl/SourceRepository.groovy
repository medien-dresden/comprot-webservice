package de.comprot.core.repository.impl

import de.comprot.core.model.ComprotEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.ParameterizedRowMapper

class SourceRepository<E> {

    @Autowired NamedParameterJdbcTemplate template

    Page<ComprotEntity> fetch(String rowCountQuery, String rowFetchQuery,
                              Map<String, ?> arguments, Pageable pageable, ParameterizedRowMapper rowMapper) {

        def total = template.queryForObject(rowCountQuery, arguments, Long)
        def page = []

        rowFetchQuery += ' LIMIT :limit OFFSET :offset'

        arguments.with {
            limit  = pageable.pageSize
            offset = pageable.pageNumber * pageable.pageSize
        }

        template.query(rowFetchQuery, arguments, { resultSet ->
            while (resultSet.next()) { page << rowMapper.mapRow(resultSet, -1) }
            return page

        } as ResultSetExtractor)

        new PageImpl<E>(page, pageable, total)
    }

}
