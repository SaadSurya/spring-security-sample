package com.sample.sso.rest.controllers;

import com.sample.sso.entities.ResourceEntity;
import com.sample.sso.services.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ResourceEntity>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceEntity> getById(@PathVariable("id") Integer id) {
        Optional<ResourceEntity> resourceEntity = this.service.getById(id);
        return resourceEntity
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @PostMapping("/save")
    public ResponseEntity<ResourceEntity> save(@RequestBody ResourceEntity resource) {
        return new ResponseEntity<>(this.service.save(resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceEntity> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }

}
