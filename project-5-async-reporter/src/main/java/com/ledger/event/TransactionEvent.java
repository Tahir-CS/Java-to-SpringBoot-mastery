package com.ledger.event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent {
    private String eventType;
    private String eventId;
    private Long transactionId;
    private String fromUsername;
    private String toUsername;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime occurredAt;
    private String status;
    private String failureReason;
}
