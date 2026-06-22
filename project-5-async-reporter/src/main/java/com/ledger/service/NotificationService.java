package com.ledger.service;
import com.ledger.event.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void sendTransferReceipt(TransactionEvent event) {
        log.info("\n=== TRANSFER RECEIPT ===\nTo:      {}\nFrom:    {}\nAmount:  {} {}\nStatus:  {}\nEventId: {}\nTime:    {}\n=======================\n",
            event.getToUsername(), event.getFromUsername(), event.getAmount(), event.getCurrency(),
            event.getStatus(), event.getEventId(), event.getOccurredAt());
    }
    public void sendTransferFailureAlert(TransactionEvent event) {
        log.error("Transfer FAILED — alerting sender: username={}, reason={}", event.getFromUsername(), event.getFailureReason());
    }
}
