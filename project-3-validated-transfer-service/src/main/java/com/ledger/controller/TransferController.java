package com.ledger.controller;
import com.ledger.dto.TransferRequest;
import com.ledger.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> transfer(@Valid @RequestBody TransferRequest request) {
        transferService.transfer(request);
        return Map.of("status", "Transfer completed successfully");
    }
}
