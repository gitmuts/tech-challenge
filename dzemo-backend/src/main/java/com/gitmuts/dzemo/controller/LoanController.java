package com.gitmuts.dzemo.controller;

import com.gitmuts.dzemo.entity.Constants;
import com.gitmuts.dzemo.entity.Loan;
import com.gitmuts.dzemo.entity.User;
import com.gitmuts.dzemo.repo.LoanRepo;
import com.gitmuts.dzemo.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    LoanRepo loanRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping()
    public ResponseEntity<?> getUserLoanBalance() {
        try{
            //Get logged in user from spring security context
            log.debug("Getting loan balance for {}", Constants.LoggedInUser);
            User user = userRepo.findByUsername(Constants.LoggedInUser);
            if(user == null){
                return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
            }

            Loan loan = loanRepo.findByUser(user);

            return new ResponseEntity(loan, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error occurred, try later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
