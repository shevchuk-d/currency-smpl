package com.github.shevchuk.d.currency.service;

import com.github.shevchuk.d.currency.model.Currency;
import com.github.shevchuk.d.currency.model.CurrencyEntity;
import org.joda.time.DateTime;

/**
 * Created by dmsh0216 on 12/01/2017.
 */
public interface CurrencyService {

    void save(CurrencyEntity currency);

//    Currency findByDateAndBase(DateTime date, String base);
}
