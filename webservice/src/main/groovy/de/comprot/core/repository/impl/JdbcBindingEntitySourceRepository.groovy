package de.comprot.core.repository.impl

import de.comprot.core.model.BindingEntity
import de.comprot.core.repository.BindingEntitySourceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.simple.ParameterizedRowMapper
import org.springframework.stereotype.Repository

@Repository class JdbcBindingEntitySourceRepository
        extends SourceRepository<BindingEntity> implements BindingEntitySourceRepository {

    @Override Page<BindingEntity> findAllBindings(Pageable pageable) {
        fetch('SELECT COUNT(*) FROM BINDS_DRUG_TARGET',
                'SELECT * FROM BINDS_DRUG_TARGET AS b INNER JOIN RT_DRUG_TARGET_INTERACTION_TYPE it ' +
                        'ON b.RT_DRUG_TARGET_INTERACTION_TYPE_ID = it.ID',
                [:], pageable, { result, rowNumber -> new BindingEntity(
                        id:                 "B${result.getLong('ID')}",
                        targetId:           "T${result.getLong('TARGET_ID')}",
                        compoundId:         "C${result.getLong('COMPOUND_ID')}",
                        interactionType:    result.getString('INTERACTION_TYPE')
                )
            } as ParameterizedRowMapper
        )
    }

}
