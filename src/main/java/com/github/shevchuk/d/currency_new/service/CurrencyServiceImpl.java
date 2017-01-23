package com.github.shevchuk.d.currency_new.service;

import com.github.shevchuk.d.currency_new.model.CurrencyEntity;
import com.github.shevchuk.d.currency_new.repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmsh0216 on 12/01/2017.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService{
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void save(CurrencyEntity currency) {
        currencyRepository.save(currency);
    }

//    @Override
//    public Currency findByDateAndBase(DateTime date, String base) {
//        return null;
//    }
}
