package com.ledger.controller;
import com.ledger.dto.PaymentRequest;
import com.ledger.dto.PaymentResponse;
import com.ledger.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService ps) { this.paymentService = ps; }

    @PostMapping("/process")
    public PaymentResponse processPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }
}
