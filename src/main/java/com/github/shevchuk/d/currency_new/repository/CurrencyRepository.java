package com.github.shevchuk.d.currency_new.repository;

import com.github.shevchuk.d.currency_new.model.CurrencyEntity;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dmsh0216 on 12/01/2017.
 */
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, DateTime> {
//    Currency findByDateAndBase(DateTime date, String base);
}
