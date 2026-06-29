package com.ledger.client;
import com.ledger.dto.PaymentRequest;
import com.ledger.exception.BankApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Random;
import java.util.UUID;
import java.time.LocalDateTime;
import lombok.*;

@Component
@Slf4j
public class BankApiClient {
    private final Random random = new Random();

    public BankPaymentResponse processPayment(PaymentRequest request) {
        simulateNetworkLatency();
        if (random.nextDouble() < 0.5) {
            log.warn("Bank API FAILED for transactionRef={}", request.getTransactionRef());
            throw new BankApiException("Bank API unavailable. TransactionRef: " + request.getTransactionRef());
        }
        log.info("Bank API SUCCESS for transactionRef={}", request.getTransactionRef());
        return BankPaymentResponse.builder()
            .transactionRef(request.getTransactionRef())
            .bankTransactionId("BANK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
            .status("APPROVED")
            .approvedAt(LocalDateTime.now())
            .build();
    }
    private void simulateNetworkLatency() {
        try { Thread.sleep(100 + (long)(random.nextDouble() * 500)); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class BankPaymentResponse {
        private String transactionRef;
        private String bankTransactionId;
        private String status;
        private LocalDateTime approvedAt;
    }
}
