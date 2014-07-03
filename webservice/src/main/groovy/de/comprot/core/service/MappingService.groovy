package de.comprot.core.service

interface MappingService {

    public <T> T generate(Object source, Class<T> target)

    public <T> void transfer(Object source, T target)

    public <T,O> List<O> generateList(List<T> objects, Class<O> target)

    public <T,O> List<O> transferList(List<T> objects, Class<O> target, List<O> destination)

    public <T,O> Set<O> generateSet(Set<T> objects, Class<O> target, Set<O> destination)

    public <T,O> Set<O> transferSet(Set<T> objects, Class<O> target)

}