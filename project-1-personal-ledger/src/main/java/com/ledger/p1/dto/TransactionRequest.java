package com.ledger.p1.dto;

import com.ledger.p1.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @NotNull(message = "amount is required")
    @Positive(message = "amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "type is required")
    private TransactionType type;

    @Size(max = 255, message = "description must be <= 255 chars")
    private String description;
}
