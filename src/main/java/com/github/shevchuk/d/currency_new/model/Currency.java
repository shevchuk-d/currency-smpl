package com.github.shevchuk.d.currency_new.model;

/**
 * Created by dmsh0216 on 13/01/2017.
 */
public interface Currency {

    long getRate();

    void setRate(long rate);

    String getDateTime();

    void setDateTime(String dateTime);

    String getBase();

    void setBase(String base);

    String getTarget();

    void setTarget(String target);
}
