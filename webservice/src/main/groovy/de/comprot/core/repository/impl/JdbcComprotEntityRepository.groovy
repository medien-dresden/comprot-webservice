package de.comprot.core.repository.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.ComprotEntityRepository
import de.comprot.core.repository.util.Page
import de.comprot.core.repository.util.PaginationHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.ParameterizedRowMapper
import org.springframework.stereotype.Repository

@Repository class JdbcComprotEntityRepository implements ComprotEntityRepository {

    @Autowired NamedParameterJdbcTemplate template

    @Override Page<ComprotEntity> findAllProteins(int pageNumber, int pageSize) {
        new PaginationHelper<ComprotEntity>().fetch(template, 'SELECT COUNT(*) FROM TARGET',
                'SELECT t.*, GROUP_CONCAT(DISTINCT ts.name SEPARATOR 0x3) AS synonyms FROM TARGET t ' +
                        'LEFT JOIN TARGET_SYNONYM ts ON ts.TARGET_ID = t.ID GROUP BY t.ID ORDER BY t.id ASC',
                pageNumber, pageSize, { result, rowNumber -> new ComprotEntity(
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

}
