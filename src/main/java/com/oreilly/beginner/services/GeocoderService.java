package com.oreilly.beginner.services;

import com.oreilly.beginner.json.Response;
import com.oreilly.beginner.json.Site;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeocoderService {
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";
    private final WebClient webClient;

    public GeocoderService(WebClient.Builder webClientBuilder) {
        webClient =webClientBuilder.baseUrl("https://maps.googleapis.com").build();
    }

    public Site getLatLng(String... address) {
        String encoded = Stream.of(address)
                .map(s -> {
                    String urlencode = "";
                    try {
                        urlencode  = URLEncoder.encode(s,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return urlencode;
                })
                .collect(Collectors.joining(","));
        String path = "/maps/api/geocode/json";
        Response response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("address", encoded)
                        .queryParam("key", KEY)
                        .build())
                .retrieve()
                .bodyToMono(Response.class)
                .block(Duration.ofSeconds(2));
        return new Site(response.getFormattedAddress(),
                response.getLocation().getLat(),
                response.getLocation().getLng());
    }
}
