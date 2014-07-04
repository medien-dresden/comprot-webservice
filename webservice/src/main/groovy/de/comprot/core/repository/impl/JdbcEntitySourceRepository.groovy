package de.comprot.core.repository.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.EntitySourceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.ParameterizedRowMapper
import org.springframework.stereotype.Repository

@Repository class JdbcEntitySourceRepository implements EntitySourceRepository {

    @Autowired NamedParameterJdbcTemplate template

    @Override Page<ComprotEntity> findAllProteins(Pageable pageable) {
        fetch('SELECT COUNT(*) FROM TARGET',
                'SELECT t.*, GROUP_CONCAT(DISTINCT ts.name SEPARATOR 0x3) AS synonyms FROM TARGET t ' +
                        'LEFT JOIN TARGET_SYNONYM ts ON ts.TARGET_ID = t.ID GROUP BY t.ID ORDER BY t.id ASC',
                [:], pageable, { result, rowNumber -> new ComprotEntity(
                        type:       ComprotEntity.Type.PROTEIN,
                        comprotId:  result.getLong('ID'),
                        sourceId:   result.getString('UNIPROTAC'),
                        taxonomyId: result.getInt('NCBI_TAXONOMY_ID'),
                        name:       result.getString('NAME'),
                        synonyms:   result.getString('SYNONYMS')?.split((String) 0x3 as char)
                )
            } as ParameterizedRowMapper
        )
    }

    @Override Page<ComprotEntity> findAllDrugs(Pageable pageable) {
        fetch('SELECT COUNT(*) FROM COMPOUND',
                'SELECT c.*, GROUP_CONCAT(DISTINCT cs.name SEPARATOR 0x3) AS synonyms FROM COMPOUND c ' +
                        'LEFT JOIN COMPOUND_SYNONYM cs ON cs.COMPOUND_ID = c.ID GROUP BY c.ID ORDER BY c.id ASC',
                [:], pageable, { result, rowNumber -> new ComprotEntity(
                        type:       ComprotEntity.Type.MEDICINE,
                        comprotId:  result.getLong('ID'),
                        sourceId:   result.getString('ID'),
                        name:       result.getString('NAME'),
                        synonyms:   result.getString('SYNONYMS')?.split((String) 0x3 as char)
                )
            } as ParameterizedRowMapper
        )
    }

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

        new PageImpl<ComprotEntity>(page, pageable, total)
    }

}
