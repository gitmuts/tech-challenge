package com.gitmuts.dzemo.repo;

import com.gitmuts.dzemo.entity.Transaction;
import com.gitmuts.dzemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TransactionRepo extends PagingAndSortingRepository<Transaction, Long> {

    Page<Transaction> findAllByUserOrderByIdDesc(User user, Pageable pageable);

    Page<Transaction> findByUserCreatedAtBetween(User user, LocalDate to, LocalDate from, Pageable pageable);

    List<Transaction> findByUserAndCreatedAtBetween(User user, Date from, Date to);

    List<Transaction> findAll();

}
