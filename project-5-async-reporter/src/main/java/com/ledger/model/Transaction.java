package com.ledger.model;
import jakarta.persistence.*; import lombok.Data; import java.math.BigDecimal; import java.time.LocalDateTime;
@Data @Entity @Table(name = "transactions") public class Transaction { @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; @Column(nullable = false, precision = 19, scale = 2) private BigDecimal amount; @Enumerated(EnumType.STRING) @Column(nullable = false) private TransactionType type; private LocalDateTime createdAt; @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); } }
