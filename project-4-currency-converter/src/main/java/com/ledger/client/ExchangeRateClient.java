package com.ledger.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ledger.exception.CurrencyNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ExchangeRateClient {

    private final WebClient webClient;

    @Value("${exchange-rate.api-key}")
    private String apiKey;

    public ExchangeRateClient(WebClient.Builder webClientBuilder, @Value("${exchange-rate.base-url}") String baseUrl) {
        this.webClient = webClientBuilder
            .baseUrl(baseUrl)
            .build();
    }

    public Map<String, Double> fetchRates(String baseCurrency) {
        ExchangeRateApiResponse response = webClient.get()
            .uri("/{apiKey}/latest/{currency}", apiKey, baseCurrency.toUpperCase())
            .retrieve()
            .onStatus(
                status -> status.is4xxClientError(),
                resp -> Mono.error(new CurrencyNotFoundException("Unknown currency: " + baseCurrency)))
            .bodyToMono(ExchangeRateApiResponse.class)
            .block();

        if (response == null || response.getConversionRates() == null) {
            throw new RuntimeException("Empty response from exchange rate API");
        }

        return response.getConversionRates();
    }

    @Data
    public static class ExchangeRateApiResponse {
        private String result;

        @JsonProperty("conversion_rates")
        private Map<String, Double> conversionRates;
    }
}
