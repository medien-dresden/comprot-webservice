package de.comprot.core.repository.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.core.repository.ComprotEntitySourceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.simple.ParameterizedRowMapper
import org.springframework.stereotype.Repository

@Repository class JdbcComprotEntitySourceRepository
        extends SourceRepository<ComprotEntity> implements ComprotEntitySourceRepository {

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
                        type:       ComprotEntity.Type.DRUG,
                        comprotId:  result.getLong('ID'),
                        sourceId:   result.getString('ID'),
                        name:       result.getString('NAME'),
                        synonyms:   result.getString('SYNONYMS')?.split((String) 0x3 as char)
                )
            } as ParameterizedRowMapper
        )
    }

}
