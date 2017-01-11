package com.github.shevchuk.d.account.repository;

import com.github.shevchuk.d.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dmsh0216 on 10/01/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
