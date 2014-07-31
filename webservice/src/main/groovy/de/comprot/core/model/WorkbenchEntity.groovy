package de.comprot.core.model

import groovy.transform.EqualsAndHashCode

import javax.persistence.*
import javax.validation.constraints.NotNull

@Table(name = 'comprot_workbench')
@EqualsAndHashCode(includeFields = true, includes = 'id')
@Entity class WorkbenchEntity {

    @GeneratedValue @Id Long id

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = 'userId')
    @NotNull UserEntity user

    @NotNull String label = 'Default'

    @NotNull ComprotEntity[] targets = []

    @NotNull ComprotEntity[] compounds = []

}
