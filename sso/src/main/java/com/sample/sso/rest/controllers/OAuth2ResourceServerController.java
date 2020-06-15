package com.sample.sso.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Josh Cummings
 */
@RestController
@RequestMapping("/api/v1")
public class OAuth2ResourceServerController {




    @GetMapping("/me")
    public String index(Principal principal) {
        return String.format("Hello, %s!", ((JwtAuthenticationToken)principal).getTokenAttributes().get("user_name"));
    }

    @GetMapping("/message")
    public String message() {
        return "secret message";
    }

    @PostMapping("/message")
    public String createMessage(@RequestBody String message) {
        return String.format("Message was created. Content: %s", message);
    }
}