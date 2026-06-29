package com.ledger.dto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PaymentResponse {
    private String transactionRef;
    private String status;
    private String message;
    private String bankTransactionId;
}
