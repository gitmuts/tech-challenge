package com.gitmuts.dzemo.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    public enum TransactionType { CREDIT, DEBIT };
    public enum TransactionMode { MOBILE, BANK };
    public enum Status { SUCCESS, FAILED };


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "customer_name", nullable = false) // receiver or sender name
    private String customerName;

    @Column(name = "receiver_account_number", nullable = false) // receiver or sender name
    private String receiverAccountNo;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_mode", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionMode transactionMode;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
}
