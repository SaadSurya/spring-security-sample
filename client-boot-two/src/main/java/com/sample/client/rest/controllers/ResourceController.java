package com.sample.client.rest.controllers;

import com.sample.client.entities.ResourceEntity;
import com.sample.client.services.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResourceEntity>> getAll(Principal principal) {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceEntity> getById(Principal principal, @PathVariable("id") Integer id) {
        Optional<ResourceEntity> resourceEntity = this.service.getById(id);
        return resourceEntity
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @PostMapping("/save")
    public ResponseEntity<ResourceEntity> save(Principal principal, @RequestBody ResourceEntity resource) {
        return new ResponseEntity<>(this.service.save(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceEntity> delete(Principal principal, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }

}
