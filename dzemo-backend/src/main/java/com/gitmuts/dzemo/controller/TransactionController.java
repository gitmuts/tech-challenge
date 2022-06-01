package com.gitmuts.dzemo.controller;

import com.gitmuts.dzemo.entity.Constants;
import com.gitmuts.dzemo.entity.Transaction;
import com.gitmuts.dzemo.entity.User;
import com.gitmuts.dzemo.model.ListResponse;
import com.gitmuts.dzemo.repo.TransactionRepo;
import com.gitmuts.dzemo.repo.UserRepo;
import com.gitmuts.dzemo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentTransactions() {
        try{
            //Get logged in user from spring security context
            log.debug("Getting user transactions for {}", Constants.LoggedInUser);
            User user = userRepo.findByUsername(Constants.LoggedInUser);
            if(user == null){
                return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
            }

            Page<Transaction> transactionPage = transactionService.getRecentTransactions(user);

            return new ResponseEntity(new ListResponse(transactionPage.getContent(), transactionPage.getTotalPages(), transactionPage.getNumberOfElements()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error occurred, try later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getTransactions(@RequestParam("start_date") String startDateString, @RequestParam("end_date") String endDateString) {
        try{
            //Get logged in user from spring security context
            log.debug("Getting user transactions for {}", Constants.LoggedInUser);
            User user = userRepo.findByUsername(Constants.LoggedInUser);
            if(user == null){
                return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
            }

            List<Transaction> transactionList =  transactionService.getAllTransactions(user, startDateString, endDateString);

            return new ResponseEntity(transactionList, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error ", e);
            return new ResponseEntity("Error occurred, try later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
