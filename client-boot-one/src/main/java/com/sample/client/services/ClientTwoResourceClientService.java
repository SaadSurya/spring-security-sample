package com.sample.client.services;

import com.sample.client.entities.ResourceEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ClientTwoResourceClientService {

    @Value("${clienttwo.resource.url:}")
    private String clientTwoResourceUrl;

    private final WebClient webClient;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public ClientTwoResourceClientService(
            WebClient webClient,
            OAuth2AuthorizedClientService authorizedClientService
    ) {
        this.webClient = webClient;
        this.authorizedClientService = authorizedClientService;
    }

    public List<ResourceEntity> getAll(Principal principal) {
        return this.webClient
                .get()
                .uri(clientTwoResourceUrl + "list")
                .header("Authorization", "Bearer " + getToken(principal))
                .retrieve()
                .bodyToFlux(ResourceEntity.class)
                .collectList()
                .block();
    }

    public Optional<ResourceEntity> getById(Principal principal, Integer id) {
        ResourceEntity response = this.webClient
                .get()
                .uri(clientTwoResourceUrl + id)
                .header("Authorization", "Bearer " + getToken(principal))
                .retrieve()
                .bodyToMono(ResourceEntity.class)
                .block();
        return response == null ? Optional.empty() : Optional.of(response);
    }

    public ResourceEntity save(Principal principal, ResourceEntity resource) {
        return this.webClient
                .post()
                .uri(clientTwoResourceUrl + "save")
                .header("Authorization", "Bearer " + getToken(principal))
                .body(Mono.just(resource), ResourceEntity.class)
                .retrieve()
                .bodyToMono(ResourceEntity.class)
                .block();
    }

    public ResourceEntity delete(Principal principal, Integer id) {
        return this.webClient
                .delete()
                .uri(clientTwoResourceUrl + id)
                .header("Authorization", "Bearer " + getToken(principal))
                .retrieve()
                .bodyToMono(ResourceEntity.class)
                .block();
    }

    private String getToken(Principal principal) {
        String token;
        if (principal instanceof JwtAuthenticationToken) {
            token = ((JwtAuthenticationToken) principal).getToken().getTokenValue();
        } else {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) principal;
            token = this.authorizedClientService
                    .loadAuthorizedClient(
                            oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                            principal.getName()
                    ).getAccessToken()
                    .getTokenValue();
        }
        return token;
    }
}
