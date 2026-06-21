package com.ledger.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ConversionRequest {

    @NotBlank(message = "Source currency is required")
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters (e.g. USD)")
    private String from;

    @NotBlank(message = "Target currency is required")
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters (e.g. PKR)")
    private String to;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Digits(integer = 15, fraction = 2, message = "Amount format invalid")
    private BigDecimal amount;
}
