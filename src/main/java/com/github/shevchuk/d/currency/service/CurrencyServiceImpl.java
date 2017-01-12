package com.github.shevchuk.d.currency.service;

import com.github.shevchuk.d.currency.model.Currency;
import com.github.shevchuk.d.currency.model.CurrencyEntity;
import com.github.shevchuk.d.currency.repository.CurrencyRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmsh0216 on 12/01/2017.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void save(CurrencyEntity currency) {
        currencyRepository.save(currency);
    }

//    @Override
//    public Currency findByDateAndBase(DateTime date, String base) {
//        return null;
//    }
}
