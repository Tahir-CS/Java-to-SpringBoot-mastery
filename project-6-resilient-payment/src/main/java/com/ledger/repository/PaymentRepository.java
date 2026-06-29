package com.ledger.repository;
import com.ledger.model.Payment;
import com.ledger.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatusAndCreatedAtAfter(PaymentStatus status, LocalDateTime time);
}
