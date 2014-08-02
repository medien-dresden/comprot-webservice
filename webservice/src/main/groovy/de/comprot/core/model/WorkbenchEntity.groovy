package de.comprot.core.model

import groovy.transform.EqualsAndHashCode

import javax.persistence.*
import javax.validation.constraints.NotNull

@Table(name = 'comprot_workbench')
@EqualsAndHashCode(includeFields = true, includes = 'id')
@Entity class WorkbenchEntity {

    @GeneratedValue @Id Long id

    @NotNull String label = 'Default'

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = 'userId')
    @NotNull UserEntity user

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = 'comprot_workbench_target',
            joinColumns = [ @JoinColumn(name = 'workbenchId') ],
            inverseJoinColumns = [ @JoinColumn(name = 'entityRefId') ])
    @NotNull List<ComprotEntityRef> targets = []

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = 'comprot_workbench_compound',
            joinColumns = [ @JoinColumn(name = 'workbenchId') ],
            inverseJoinColumns = [ @JoinColumn(name = 'entityRefId') ])
    @NotNull List<ComprotEntityRef> compounds = []

}
