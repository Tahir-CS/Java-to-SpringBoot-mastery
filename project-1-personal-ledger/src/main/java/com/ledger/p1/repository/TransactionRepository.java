package com.ledger.p1.repository;

import com.ledger.p1.model.Transaction;
import com.ledger.p1.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Example derived query to reinforce how method names map to SQL generation.
    List<Transaction> findByType(TransactionType type);
}
