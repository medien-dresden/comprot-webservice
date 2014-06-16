package de.comprot.core.service.impl

import de.comprot.core.service.MappingService
import org.dozer.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service class DozerMappingService implements MappingService {

    @Autowired Mapper mapper

    @Override public <T> T map(Object source, Class<T> target) {
        mapper.map(source, target)
    }

    @Override public <T> void map(Object source, T target) {
        mapper.map(source, target)
    }

    @Override public <T,O> List<O> map(List<T> objects, Class<O> target) {
        (List<O>) internalMap(objects, target, (List<O>) [])
    }

    @Override public <T,O> List<O> map(List<T> objects, Class<O> target, List<O> destination) {
        (List<O>) internalMap(objects, target, destination)
    }

    @Override public <T,O> Set<O> map(Set<T> objects, Class<O> target, Set<O> destination) {
        (Set<O>) internalMap(objects, target, destination)
    }

    @Override public <T,O> Set<O> map(Set<T> objects, Class<O> target) {
        map(objects, target, (Set<O>) [])
    }

    private <T, O> Collection<O> internalMap(Collection<T> objects, Class<O> target, Collection<O> destination) {
        destination.addAll(objects.collect({ mapper.map(it, target) }))
        return destination
    }

}
