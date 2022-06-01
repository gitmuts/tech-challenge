package com.gitmuts.dzemo.repo;

import com.gitmuts.dzemo.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String name);
}
