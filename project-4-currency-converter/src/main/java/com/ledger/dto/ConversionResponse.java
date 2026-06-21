package com.ledger.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ConversionResponse {
    private String from;
    private String to;
    private BigDecimal amount;
    private BigDecimal result;
    private BigDecimal rate;
    private LocalDateTime timestamp;

    @Builder.Default
    private boolean fromCache = false;
}
