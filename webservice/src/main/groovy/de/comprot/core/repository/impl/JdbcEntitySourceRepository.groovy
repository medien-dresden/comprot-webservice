package de.comprot.core.repository.impl

import de.comprot.core.model.ComprotEntity
import de.comprot.common.Page
import de.comprot.core.repository.EntitySourceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.ParameterizedRowMapper
import org.springframework.stereotype.Repository

@Repository class JdbcEntitySourceRepository implements EntitySourceRepository {

    @Autowired NamedParameterJdbcTemplate template

    @Override Page<ComprotEntity> findAllProteins(int pageNumber, int pageSize) {
        fetch('SELECT COUNT(*) FROM TARGET',
                'SELECT t.*, GROUP_CONCAT(DISTINCT ts.name SEPARATOR 0x3) AS synonyms FROM TARGET t ' +
                        'LEFT JOIN TARGET_SYNONYM ts ON ts.TARGET_ID = t.ID GROUP BY t.ID ORDER BY t.id ASC',
                [:], pageNumber, pageSize, { result, rowNumber -> new ComprotEntity(
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

    Page<ComprotEntity> fetch(String rowCountQuery, String rowFetchQuery,
                      Map<String, ?> arguments, int pageNumber, final int pageSize, ParameterizedRowMapper rowMapper) {

        // determine how many rows are available
        def rowCount = template.queryForObject(rowCountQuery, arguments, Long.class)

        // calculate the number of pages
        def pageCount = rowCount / pageSize
        if (rowCount > pageSize * pageCount) {
            pageCount++
        }

        // create the page object
        def page = new Page(pageNumber: pageNumber, pagesAvailable: pageCount)

        // fetch a single page of results
        def startRow = (pageNumber - 1) * pageSize

        template.query(rowFetchQuery, arguments, { resultSet ->
            def pageItems = page.getItems()
            def currentRow = 0

            while (resultSet.next() && currentRow < startRow + pageSize) {
                if (currentRow >= startRow) {
                    //noinspection GroovyAssignabilityCheck
                    pageItems.add(rowMapper.mapRow(resultSet, currentRow))
                }

                currentRow++
            }

            return page
        } as ResultSetExtractor)

        return page
    }

}
