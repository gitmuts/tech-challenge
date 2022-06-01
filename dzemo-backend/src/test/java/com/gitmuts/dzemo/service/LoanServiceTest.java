package com.gitmuts.dzemo.service;

import com.gitmuts.dzemo.entity.Loan;
import com.gitmuts.dzemo.entity.User;
import com.gitmuts.dzemo.repo.LoanRepo;
import com.gitmuts.dzemo.repo.TransactionRepo;
import com.gitmuts.dzemo.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepo loanRepo;

    @Mock
    private UserRepo userRepo;

    User user;

    Loan loan;

    @BeforeEach
    public void setup() {

        user = new User();
        user.setName("Marl");
        user.setActive(1);
        user.setUsername("Marl");
        user.setPassword("$2a$10$Vc8ANYhsuL7BUThuBrVlIuZWNSWzftJbi6ULLuD7QZdMDJjPNOj8G");
        userRepo.save(user);

        loan = new Loan();
        loan.setId(1);
        loan.setUser(user);
        loan.setAmount(BigDecimal.valueOf(12000));
    }

    @Test
    public void getLoanByUser(){
        given(loanRepo.findByUser(user)).willReturn(loan);

        Loan loan1 = loanRepo.findByUser(user);
        assertThat(loan1).isNotNull();
        assertThat(loan1.getId() == 1);
    }
}