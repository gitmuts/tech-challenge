package com.gitmuts.dzemo.service;

import com.gitmuts.dzemo.entity.Transaction;
import com.gitmuts.dzemo.entity.User;
import com.gitmuts.dzemo.repo.TransactionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    public List<Transaction> getAllTransactions(User user, String startDateString, String endDateString){
        try{
            LocalDate startDate;
            LocalDateTime endDate;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            if(startDateString == null || startDateString.isEmpty()){
                startDate = LocalDate.now().minusDays(7);
            } else {
                startDate = LocalDate.parse(startDateString, dateTimeFormatter);
            }

            if(endDateString == null || endDateString.isEmpty()){
                endDate = LocalDateTime.now();
            } else {
                endDate = LocalDate.parse(endDateString, dateTimeFormatter).atTime(LocalTime.MAX);
            }

            List<Transaction> transactionList = transactionRepo.findByUserAndCreatedAtBetween(user, Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant()));

            return transactionList;
        } catch (Exception e){
            log.error("Error occurred while calling getAllTransactions ", e);
            return new ArrayList<>();
        }
    }

    public Page<Transaction> getRecentTransactions(User user){
        try{
            int page=0;
            int perPage = 10; //Get last 10 transactions
            Pageable pageable = PageRequest.of(page, perPage);

            Page<Transaction> transactionPage = transactionRepo.findAllByUserOrderByIdDesc(user, pageable);

            return  transactionPage;
        } catch (Exception e){
            log.error("Error occurred while calling getRecentTransactions ", e);
            return  Page.empty();
        }
    }
}
