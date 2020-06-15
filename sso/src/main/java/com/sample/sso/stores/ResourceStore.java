package com.sample.sso.stores;

import com.sample.sso.entities.ResourceEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ResourceStore {
    private List<ResourceEntity> resources;

    public List<ResourceEntity> getResources() {
        if(resources == null) {
            resources = new LinkedList<>();
            resources.add(new ResourceEntity(1, "Resource One", "This is resource one of sso."));
            resources.add(new ResourceEntity(2, "Resource Two", "This is resource two of sso."));
            resources.add(new ResourceEntity(3, "Resource Three", "This is resource three of sso."));
        }
        return resources;
    }
}
