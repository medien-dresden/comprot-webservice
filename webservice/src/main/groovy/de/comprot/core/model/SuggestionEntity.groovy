package de.comprot.core.model

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(
        includeFields = true,
        includes = 'label')
class SuggestionEntity {

    String label

    int hits

}
