package com.sample.sso.services;

import com.sample.sso.entities.ResourceEntity;
import com.sample.sso.stores.ResourceStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceStore store;

    public ResourceService(ResourceStore store) {
        this.store = store;
    }

    public List<ResourceEntity> getAll() {
        return this.store.getResources();
    }

    public Optional<ResourceEntity> getById(Integer id) {
        return this.store.getResources().stream().filter(r -> r.getId().equals(id)).findAny();
    }

    public ResourceEntity save(ResourceEntity resource) {
        List<ResourceEntity> resourceEntities = this.store.getResources();
        int storedAt = resourceEntities.indexOf(resource);
        if(storedAt >= 0) {
            resourceEntities.set(storedAt, resource);
        } else {
            resourceEntities.add(resource);
        }
        return resource;
    }

    public ResourceEntity delete(Integer id) {
        List<ResourceEntity> resourceEntities = this.store.getResources();
        Optional<ResourceEntity> resourceEntity = resourceEntities.stream().filter(r -> r.getId().equals(id)).findAny();
        if(resourceEntity.isPresent()) {
            resourceEntities.remove(resourceEntity.get());
            return resourceEntity.get();
        }
        throw new RuntimeException("Resource not found to be deleted.");
    }
}
