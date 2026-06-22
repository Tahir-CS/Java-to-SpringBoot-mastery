package com.ledger.dto;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
@Data public class TransferRequest { @NotNull private Long fromUserId; @NotNull private Long toUserId; @NotNull @Positive private BigDecimal amount; @Size(max = 200) private String description; }
