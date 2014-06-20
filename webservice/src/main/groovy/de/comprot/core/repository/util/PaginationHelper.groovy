package de.comprot.core.repository.util

import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.ParameterizedRowMapper

/** built on http://www.codefutures.com/spring-pagination/ */
class PaginationHelper<E> {

    static Page<E> fetch(NamedParameterJdbcTemplate template, String rowCountQuery, String rowFetchQuery,
                  Map<String, ?> arguments, int pageNumber, final int pageSize, ParameterizedRowMapper<E> rowMapper) {

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

    static Page fetch(NamedParameterJdbcTemplate template, String rowCountQuery, String rowFetchQuery,
                  int pageNumber, int pageSize, ParameterizedRowMapper<E> rowMapper) {
        fetch(template, rowCountQuery, rowFetchQuery, [:], pageNumber, pageSize, rowMapper)
    }

}