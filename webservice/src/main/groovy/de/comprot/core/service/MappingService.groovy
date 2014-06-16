package de.comprot.core.service

interface MappingService {

    public <T> T map(Object source, Class<T> target)

    public <T> void map(Object source, T target)

    public <T,O> List<O> map(List<T> objects, Class<O> target)

    public <T,O> List<O> map(List<T> objects, Class<O> target, List<O> destination)

    public <T,O> Set<O> map(Set<T> objects, Class<O> target, Set<O> destination)

    public <T,O> Set<O> map(Set<T> objects, Class<O> target)

}