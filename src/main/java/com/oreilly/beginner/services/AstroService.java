package com.oreilly.beginner.services;

import com.oreilly.beginner.json.AstroResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service // = similar to @Component
public class AstroService {
    private final RestTemplate template;
    private final WebClient webClient;

    @Autowired
    public AstroService(
            RestTemplateBuilder rtBuilder,
            WebClient.Builder webClientBuilder
    ) {
        template = rtBuilder.build();
        webClient = webClientBuilder.baseUrl("http://api.open-notify.org").build();
    }

    // Using RestTemplate
    public AstroResult getAstronautsUsingRestTemplate() {
        String url = "http://api.open-notify.org/astros.json";
        return template.getForObject(url, AstroResult.class);
    }

    // Using WebClient
    public AstroResult getAstronautsUsingWebClient() {
        return webClient.get()
                .uri("/astros.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AstroResult.class)
                .block(Duration.ofSeconds(2));
    }
}
