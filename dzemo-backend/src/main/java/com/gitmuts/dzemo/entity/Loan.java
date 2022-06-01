package com.gitmuts.dzemo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
}
