package de.comprot.core.service

class EntityPropertyConstraintException extends RuntimeException {

    def propertyName

    EntityPropertyConstraintException(String propertyName, String message) {
        super(message)

        this.propertyName = propertyName
    }

}
