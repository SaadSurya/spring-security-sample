package com.sample.client.mvc.controllers;

import com.sample.client.services.ClientOneResourceClientService;
import com.sample.client.services.ResourceService;
import com.sample.client.services.SSOResourceClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/resources")
public class ResourcesController {

    private final ResourceService resourceService;
    private final ClientOneResourceClientService clientOneResourceClientService;
    private final SSOResourceClientService ssoResourceClientService;

    public ResourcesController(
            ResourceService resourceService,
            ClientOneResourceClientService clientOneResourceClientService,
            SSOResourceClientService ssoResourceClientService
    ) {
        this.resourceService = resourceService;
        this.clientOneResourceClientService = clientOneResourceClientService;
        this.ssoResourceClientService = ssoResourceClientService;
    }

    @GetMapping("/client-one")
    public String clientOne(Principal principal, Model model){
        model.addAttribute("data", this.clientOneResourceClientService.getAll(principal));
        return "client-one-resources";
    }

    @GetMapping("/client-two")
    public String clientTwo(Model model){
        model.addAttribute("data", this.resourceService.getAll());
        return "client-two-resources";
    }

    @GetMapping("/sso")
    public String sso(Principal principal, Model model){
        model.addAttribute("data", this.ssoResourceClientService.getAll(principal));
        return "client-one-resources";
    }
}
