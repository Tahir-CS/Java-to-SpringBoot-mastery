package com.ledger.client;
import com.ledger.dto.PaymentRequest;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@Component
@Slf4j
public class ResilientBankApiClient {
    private final BankApiClient bankApiClient;
    public ResilientBankApiClient(BankApiClient bankApiClient) { this.bankApiClient = bankApiClient; }

    @CircuitBreaker(name = "bankApi", fallbackMethod = "paymentFallback")
    @Retry(name = "bankApi")
    @TimeLimiter(name = "bankApi")
    public CompletableFuture<BankApiClient.BankPaymentResponse> processPayment(PaymentRequest request) {
        return CompletableFuture.supplyAsync(() -> bankApiClient.processPayment(request));
    }

    public CompletableFuture<BankApiClient.BankPaymentResponse> paymentFallback(PaymentRequest request, Throwable throwable) {
        if (throwable instanceof CallNotPermittedException) {
            log.warn("Circuit OPEN — fast-failing payment. TransactionRef={}, reason={}", request.getTransactionRef(), throwable.getMessage());
        } else if (throwable instanceof TimeoutException) {
            log.warn("Bank API TIMEOUT — flagging for retry. TransactionRef={}", request.getTransactionRef());
        } else {
            log.error("Bank API FAILED after retries. TransactionRef={}, error={}", request.getTransactionRef(), throwable.getMessage());
        }
        BankApiClient.BankPaymentResponse pendingResponse = BankApiClient.BankPaymentResponse.builder()
            .transactionRef(request.getTransactionRef())
            .bankTransactionId(null)
            .status("PENDING_RETRY")
            .approvedAt(null)
            .build();
        return CompletableFuture.completedFuture(pendingResponse);
    }
}
