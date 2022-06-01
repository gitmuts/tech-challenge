package com.gitmuts.dzemo.service;

import com.gitmuts.dzemo.entity.Transaction;
import com.gitmuts.dzemo.entity.User;
import com.gitmuts.dzemo.repo.TransactionRepo;
import com.gitmuts.dzemo.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepo transactionRepo;

    @Mock
    private UserRepo userRepo;


    Transaction transaction;

    User user;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setup(){

        Random random = new Random();

        user = new User();
        user.setName("Marl");
        user.setActive(1);
        user.setUsername("Marl");
        user.setPassword("$2a$10$Vc8ANYhsuL7BUThuBrVlIuZWNSWzftJbi6ULLuD7QZdMDJjPNOj8G");
        userRepo.save(user);

        Transaction.TransactionType[] transactionTypes = Transaction.TransactionType.values();
        Transaction.TransactionMode[] transactionModes = Transaction.TransactionMode.values();

        transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(BigDecimal.valueOf(random.nextInt(10000)));
        transaction.setCustomerName("TestTrans");
        transaction.setReceiverAccountNo(String.valueOf(random.nextInt(100_000-30_000) + 30000));
        transaction.setTransactionType(transactionTypes[random.nextInt(transactionTypes.length)]);
        transaction.setTransactionMode(transactionModes[random.nextInt(transactionModes.length)]);
        transaction.setStatus(Transaction.Status.SUCCESS);
        transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));

    }

    @Test
    public void returnAllTransactions(){

        Date startDate = new Date();
        Date endDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));


        given(transactionRepo.findByUserAndCreatedAtBetween(user, startDate, endDate)).willReturn(List.of(transaction));


        List<Transaction> transactionList = transactionRepo.findByUserAndCreatedAtBetween(user, startDate, endDate);


        assertThat(transactionList).isNotNull();
        assertThat(transactionList.size()).isEqualTo(1);
    }

    @Test
    public void returnZeroRecords(){

        given(transactionRepo.findAll()).willReturn(Collections.emptyList());

        List<Transaction> transactionList = transactionRepo.findAll();

        assertThat(transactionList.isEmpty());
        assertThat(transactionList.size()).isEqualTo(0);
    }
}