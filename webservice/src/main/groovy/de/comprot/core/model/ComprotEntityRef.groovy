package de.comprot.core.model

import groovy.transform.EqualsAndHashCode

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@EqualsAndHashCode
@Table(name = 'comprot_entity_ref')
@Entity class ComprotEntityRef {

    @GeneratedValue @Id Long id

    String entityId

    String toString() { entityId }

}
