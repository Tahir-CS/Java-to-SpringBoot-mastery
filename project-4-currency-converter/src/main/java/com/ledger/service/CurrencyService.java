package com.ledger.service;

import com.ledger.client.ExchangeRateClient;
import com.ledger.dto.ConversionRequest;
import com.ledger.dto.ConversionResponse;
import com.ledger.exception.CurrencyNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CurrencyService {

    private final ExchangeRateClient exchangeRateClient;

    public CurrencyService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    @Cacheable(value = "exchange-rates", key = "#baseCurrency.toUpperCase()")
    public Map<String, Double> getRates(String baseCurrency) {
        System.out.println("CACHE MISS — calling external API for: " + baseCurrency);
        return exchangeRateClient.fetchRates(baseCurrency);
    }

    public ConversionResponse convert(ConversionRequest request) {
        String from = request.getFrom().toUpperCase();
        String to   = request.getTo().toUpperCase();

        if (from.equals(to)) {
            return ConversionResponse.builder()
                .from(from).to(to)
                .amount(request.getAmount())
                .result(request.getAmount())
                .rate(BigDecimal.ONE)
                .fromCache(true)
                .timestamp(LocalDateTime.now())
                .build();
        }

        Map<String, Double> rates = getRates(from);

        if (!rates.containsKey(to)) {
            throw new CurrencyNotFoundException("Unsupported target currency: " + to);
        }

        double rate = rates.get(to);

        BigDecimal rateDecimal = BigDecimal.valueOf(rate);
        BigDecimal result = request.getAmount()
            .multiply(rateDecimal)
            .setScale(4, RoundingMode.HALF_UP);

        return ConversionResponse.builder()
            .from(from)
            .to(to)
            .amount(request.getAmount())
            .result(result)
            .rate(rateDecimal)
            .timestamp(LocalDateTime.now())
            .build();
    }

    @CacheEvict(value = "exchange-rates", key = "#baseCurrency.toUpperCase()")
    public void evictRates(String baseCurrency) {
        System.out.println("Cache evicted for: " + baseCurrency);
    }

    @CacheEvict(value = "exchange-rates", allEntries = true)
    public void evictAllRates() {
        System.out.println("All exchange rate caches cleared");
    }
}
