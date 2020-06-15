package com.sample.client.mvc.controllers;

import com.sample.client.rest.controllers.ResourceController;
import com.sample.client.services.ClientTwoResourceClientService;
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
    private final ClientTwoResourceClientService clientTwoResourceClientService;
    private final SSOResourceClientService ssoResourceClientService;

    public ResourcesController(
            ResourceService resourceService,
            ClientTwoResourceClientService clientTwoResourceClientService,
            SSOResourceClientService ssoResourceClientService
    ) {
        this.resourceService = resourceService;
        this.clientTwoResourceClientService = clientTwoResourceClientService;
        this.ssoResourceClientService = ssoResourceClientService;
    }

    @GetMapping("/client-one")
    public String clientOne(Model model){
        model.addAttribute("data", this.resourceService.getAll());
        return "client-one-resources";
    }

    @GetMapping("/client-two")
    public String clientTwo(Principal principal, Model model){
        model.addAttribute("data", this.clientTwoResourceClientService.getAll(principal));
        return "client-two-resources";
    }

    @GetMapping("/sso")
    public String sso(Principal principal, Model model){
        model.addAttribute("data", this.ssoResourceClientService.getAll(principal));
        return "client-one-resources";
    }
}
