package com.gitmuts.dzemo.repo;

import com.gitmuts.dzemo.entity.Loan;
import com.gitmuts.dzemo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepo extends CrudRepository<Loan, Long> {

    Loan findByUser(User user);
}
