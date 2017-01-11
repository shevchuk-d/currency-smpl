package com.github.shevchuk.d.account.service;

import com.github.shevchuk.d.account.model.User;

/**
 * Created by dmsh0216 on 10/01/2017.
 */
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
