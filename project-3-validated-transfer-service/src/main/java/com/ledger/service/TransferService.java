package com.ledger.service;
import com.ledger.dto.TransferRequest;
import com.ledger.exception.InsufficientFundsException;
import com.ledger.exception.SelfTransferException;
import com.ledger.model.User;
import com.ledger.model.Transaction;
import com.ledger.model.TransactionType;
import com.ledger.repository.TransactionRepository;
import com.ledger.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public TransferService(UserRepository userRepository,
                           TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void transfer(TransferRequest request) {

        if (request.getFromUserId().equals(request.getToUserId())) {
            throw new SelfTransferException("Cannot transfer money to yourself");
        }

        User sender = userRepository.findById(request.getFromUserId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User recipient = userRepository.findById(request.getToUserId())
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Sender does not have enough balance");
        }

        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        recipient.setBalance(recipient.getBalance().add(request.getAmount()));

        Transaction debit = new Transaction();
        debit.setAmount(request.getAmount());
        debit.setType(TransactionType.EXPENSE);
        debit.setDescription("Transfer to user " + recipient.getId() + ": " + request.getDescription());
        transactionRepository.save(debit);

        Transaction credit = new Transaction();
        credit.setAmount(request.getAmount());
        credit.setType(TransactionType.INCOME);
        credit.setDescription("Transfer from user " + sender.getId() + ": " + request.getDescription());
        transactionRepository.save(credit);

        userRepository.save(sender);
        userRepository.save(recipient);
    }
}
