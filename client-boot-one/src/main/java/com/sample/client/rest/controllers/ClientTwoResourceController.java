package com.sample.client.rest.controllers;

import com.sample.client.entities.ResourceEntity;
import com.sample.client.services.ClientTwoResourceClientService;
import com.sample.client.services.SSOResourceClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clienttwo/resource")
public class ClientTwoResourceController {

    private final ClientTwoResourceClientService service;

    public ClientTwoResourceController(ClientTwoResourceClientService service) {
        this.service = service;
    }

    @GetMapping("/list")

    public ResponseEntity<List<ResourceEntity>> getAll(Principal principal) {
        return new ResponseEntity<>(this.service.getAll(principal), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceEntity> getById(Principal principal, @PathVariable("id") Integer id) {
        Optional<ResourceEntity> resourceEntity = this.service.getById(principal, id);
        return resourceEntity
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @PostMapping("/save")
    public ResponseEntity<ResourceEntity> save(Principal principal, @RequestBody ResourceEntity resource) {
        return new ResponseEntity<>(this.service.save(principal, resource), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceEntity> delete(Principal principal, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(this.service.delete(principal, id), HttpStatus.OK);
    }
}
