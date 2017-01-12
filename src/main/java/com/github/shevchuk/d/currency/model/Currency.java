package com.github.shevchuk.d.currency.model;

import org.joda.time.DateTime;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by dmsh0216 on 11/01/2017.
 */
public interface Currency {
    DateTime getDate();

    void setDate(DateTime date);

    String getBase();

    void setBase(String base);

    HashMap<String, BigInteger> getRatesHM();

    void setRatesHM(HashMap<String, BigInteger> ratesHM);

    String getRates();

    void setRates(String rates);
}
