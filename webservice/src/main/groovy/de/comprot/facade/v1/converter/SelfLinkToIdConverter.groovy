package de.comprot.facade.v1.converter

import org.dozer.CustomConverter
import org.springframework.hateoas.Link

class SelfLinkToIdConverter implements CustomConverter {

    @Override Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        destination = (source as Link).href.tokenize('/').last()
    }

}
