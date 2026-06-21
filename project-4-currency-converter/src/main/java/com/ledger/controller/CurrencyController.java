package com.ledger.controller;

import com.ledger.dto.ConversionRequest;
import com.ledger.dto.ConversionResponse;
import com.ledger.service.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/convert")
    public ConversionResponse convert(@Valid @RequestBody ConversionRequest request) {
        return currencyService.convert(request);
    }

    @GetMapping("/rates/{baseCurrency}")
    public Map<String, Object> getRates(@PathVariable String baseCurrency) {
        Map<String, Double> rates = currencyService.getRates(baseCurrency.toUpperCase());
        return Map.of(
            "base", baseCurrency.toUpperCase(),
            "rates", rates,
            "fetchedAt", LocalDateTime.now().toString()
        );
    }

    @DeleteMapping("/rates/{baseCurrency}/cache")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void evictCache(@PathVariable String baseCurrency) {
        currencyService.evictRates(baseCurrency);
    }
}
