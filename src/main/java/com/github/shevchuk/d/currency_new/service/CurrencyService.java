package com.github.shevchuk.d.currency_new.service;

import com.github.shevchuk.d.currency_new.model.CurrencyEntity;

/**
 * Created by dmsh0216 on 12/01/2017.
 */
public interface CurrencyService {

    void save(CurrencyEntity currency);

//    Currency findByDateAndBase(DateTime date, String base);
}
