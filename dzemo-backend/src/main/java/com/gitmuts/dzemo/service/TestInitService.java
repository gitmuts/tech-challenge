package com.gitmuts.dzemo.service;

import com.gitmuts.dzemo.entity.Loan;
import com.gitmuts.dzemo.entity.Transaction;
import com.gitmuts.dzemo.entity.User;
import com.gitmuts.dzemo.repo.LoanRepo;
import com.gitmuts.dzemo.repo.TransactionRepo;
import com.gitmuts.dzemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class TestInitService {


    @Autowired
    UserRepo userRepo;

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    LoanRepo loanRepo;

    Random random = new Random();

    @Bean
    public void initUser(){
        User user = userRepo.findByUsername("Marl");
        if(user == null){
            user = new User();
            user.setName("Marl");
            user.setActive(1);
            user.setUsername("Marl");
            user.setPassword("$2a$10$Vc8ANYhsuL7BUThuBrVlIuZWNSWzftJbi6ULLuD7QZdMDJjPNOj8G");
            userRepo.save(user);
        }

        if(loanRepo.count() < 1){
            Loan loan = new Loan();
            loan.setUser(user);
            loan.setAmount(BigDecimal.valueOf(12000));
            loanRepo.save(loan);
        }

    }

    @Bean
    public void testTransactions(){
        User user = userRepo.findByUsername("Marl");

        Transaction.TransactionType[] transactionTypes = Transaction.TransactionType.values();
        Transaction.TransactionMode[] transactionModes = Transaction.TransactionMode.values();
        for(int i=0; i < 100; i++) {
            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setAmount(BigDecimal.valueOf(random.nextInt(10000)));
            transaction.setCustomerName("Test"+i);
            transaction.setReceiverAccountNo(String.valueOf(random.nextInt(100_000-30_000) + 30000));
            transaction.setTransactionType(transactionTypes[random.nextInt(transactionTypes.length)]);
            transaction.setTransactionMode(transactionModes[random.nextInt(transactionModes.length)]);
            transaction.setStatus(Transaction.Status.SUCCESS);
            transactionRepo.save(transaction);
        }
    }
}
