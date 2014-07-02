package de.comprot.facade.v1.model

import de.comprot.core.model.ComprotEntity

class SearchResultDto {

    int pageNumber

    int totalPages

    int totalElements

    List<ComprotEntity> content

}
