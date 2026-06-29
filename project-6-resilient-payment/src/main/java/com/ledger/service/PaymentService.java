package com.ledger.service;
import com.ledger.client.BankApiClient;
import com.ledger.client.ResilientBankApiClient;
import com.ledger.dto.PaymentRequest;
import com.ledger.dto.PaymentResponse;
import com.ledger.model.Payment;
import com.ledger.model.PaymentStatus;
import com.ledger.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {
    private final ResilientBankApiClient resilientBankApiClient;
    private final PaymentRepository paymentRepository;

    public PaymentService(ResilientBankApiClient client, PaymentRepository repo) {
        this.resilientBankApiClient = client;
        this.paymentRepository = repo;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        String transactionRef = "TXN-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
        request.setTransactionRef(transactionRef);

        Payment payment = Payment.builder()
            .transactionRef(transactionRef)
            .amount(request.getAmount())
            .currency(request.getCurrency())
            .status(PaymentStatus.INITIATED)
            .createdAt(LocalDateTime.now())
            .build();
        paymentRepository.save(payment);

        try {
            BankApiClient.BankPaymentResponse bankResponse = resilientBankApiClient.processPayment(request).get();
            PaymentStatus finalStatus = bankResponse.getStatus().equals("APPROVED") ? PaymentStatus.APPROVED : PaymentStatus.PENDING_RETRY;
            payment.setStatus(finalStatus);
            payment.setBankTransactionId(bankResponse.getBankTransactionId());
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
            
            log.info("Payment processed: ref={}, status={}, bankTxnId={}", transactionRef, finalStatus, bankResponse.getBankTransactionId());

            return PaymentResponse.builder()
                .transactionRef(transactionRef)
                .status(finalStatus.name())
                .message(finalStatus == PaymentStatus.APPROVED ? "Payment approved" : "Payment queued for processing")
                .bankTransactionId(bankResponse.getBankTransactionId())
                .build();
        } catch (Exception e) {
            log.error("Unexpected payment processing error: ref={}", transactionRef, e);
            payment.setStatus(PaymentStatus.FAILED);
            payment.setFailureReason(e.getMessage());
            paymentRepository.save(payment);
            throw new RuntimeException("Payment processing failed: " + e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 300_000)
    public void retryPendingPayments() {
        List<Payment> pendingPayments = paymentRepository.findByStatusAndCreatedAtAfter(
            PaymentStatus.PENDING_RETRY, LocalDateTime.now().minusHours(24));
        if (pendingPayments.isEmpty()) return;
        log.info("Retrying {} pending payments", pendingPayments.size());
        for (Payment payment : pendingPayments) {
            try {
                PaymentRequest retryRequest = PaymentRequest.builder()
                    .transactionRef(payment.getTransactionRef())
                    .amount(payment.getAmount())
                    .currency(payment.getCurrency())
                    .build();
                BankApiClient.BankPaymentResponse response = resilientBankApiClient.processPayment(retryRequest).get();
                if ("APPROVED".equals(response.getStatus())) {
                    payment.setStatus(PaymentStatus.APPROVED);
                    payment.setBankTransactionId(response.getBankTransactionId());
                    payment.setUpdatedAt(LocalDateTime.now());
                    paymentRepository.save(payment);
                    log.info("Retry SUCCEEDED for ref={}", payment.getTransactionRef());
                }
            } catch (Exception e) {
                log.error("Retry failed for ref={}", payment.getTransactionRef());
            }
        }
    }
}
