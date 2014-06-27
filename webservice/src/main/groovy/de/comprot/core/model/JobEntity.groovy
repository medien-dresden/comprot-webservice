package de.comprot.core.model

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(
        includeFields = true,
        includes = 'name')
class JobEntity {

    String name

    String id

    Date since

}
