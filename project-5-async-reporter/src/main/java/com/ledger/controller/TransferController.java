package com.ledger.controller;
import com.ledger.dto.TransferRequest;
import com.ledger.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService ts;
    public TransferController(TransferService ts) { this.ts = ts; }
    @PostMapping @ResponseStatus(HttpStatus.OK)
    public Map<String, String> transfer(@Valid @RequestBody TransferRequest req) { ts.transfer(req); return Map.of("status", "Transfer completed"); }
}
