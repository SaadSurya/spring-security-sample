package com.sample.sso.rest.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilController {

    private final PasswordEncoder passwordEncoder;

    public UtilController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/encode/secret")
    public String encodeClientSecret(@RequestParam("secret") String secret) {
        return passwordEncoder.encode(secret);
    }

    @GetMapping("/encode/password")
    public String encodeUserPassword(@RequestParam("password") String password) {
        return passwordEncoder.encode(password);
    }

}
