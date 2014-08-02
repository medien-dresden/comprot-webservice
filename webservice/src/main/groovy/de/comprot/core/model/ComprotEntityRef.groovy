package de.comprot.core.model

import groovy.transform.EqualsAndHashCode

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@EqualsAndHashCode
@Table(name = 'comprot_entity_ref')
@Entity class ComprotEntityRef {

    @Id String id

    String toString() { id }

    def isTargetRef() { id.startsWith('T') }

    def isCompoundRef() { id.startsWith('C') }

}
