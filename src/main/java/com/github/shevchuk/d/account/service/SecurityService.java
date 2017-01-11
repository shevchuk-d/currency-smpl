package com.github.shevchuk.d.account.service;

/**
 * Created by dmsh0216 on 10/01/2017.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
