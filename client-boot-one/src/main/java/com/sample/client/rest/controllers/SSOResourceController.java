package com.sample.client.rest.controllers;

import com.sample.client.entities.ResourceEntity;
import com.sample.client.services.ResourceService;
import com.sample.client.services.SSOResourceClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sso/resource")
public class SSOResourceController {

    private final SSOResourceClientService service;

    public SSOResourceController(SSOResourceClientService service) {
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
