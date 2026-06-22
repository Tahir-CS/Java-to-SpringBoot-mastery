package com.ledger.service;
import com.ledger.dto.TransferRequest;
import com.ledger.event.TransactionEvent;
import com.ledger.messaging.TransactionEventProducer;
import com.ledger.model.Transaction;
import com.ledger.model.TransactionType;
import com.ledger.model.User;
import com.ledger.repository.TransactionRepository;
import com.ledger.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransferService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionEventProducer eventProducer;
    public TransferService(UserRepository u, TransactionRepository t, TransactionEventProducer p) { this.userRepository = u; this.transactionRepository = t; this.eventProducer = p; }

    @Transactional
    public void transfer(TransferRequest request) {
        if (request.getFromUserId().equals(request.getToUserId())) throw new RuntimeException("Cannot transfer to self");
        User sender = userRepository.findById(request.getFromUserId()).orElseThrow();
        User recipient = userRepository.findById(request.getToUserId()).orElseThrow();
        if (sender.getBalance().compareTo(request.getAmount()) < 0) throw new RuntimeException("Insufficient funds");

        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        recipient.setBalance(recipient.getBalance().add(request.getAmount()));

        Transaction debit = new Transaction(); debit.setAmount(request.getAmount()); debit.setType(TransactionType.EXPENSE); transactionRepository.save(debit);
        Transaction credit = new Transaction(); credit.setAmount(request.getAmount()); credit.setType(TransactionType.INCOME); transactionRepository.save(credit);
        userRepository.save(sender); userRepository.save(recipient);

        TransactionEvent event = TransactionEvent.builder()
            .eventId(UUID.randomUUID().toString())
            .eventType("TRANSFER_COMPLETED")
            .transactionId(debit.getId())
            .fromUsername(sender.getUsername())
            .toUsername(recipient.getUsername())
            .amount(request.getAmount())
            .currency("USD")
            .occurredAt(LocalDateTime.now())
            .status("SUCCESS")
            .build();
        eventProducer.publishTransferCompleted(event);
    }
}
