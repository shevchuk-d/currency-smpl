package com.github.shevchuk.d.currency_new.model;

import org.springframework.core.style.ToStringCreator;

/**
 * Created by dmsh0216 on 13/01/2017.
 */
public class CurrencyObject implements Currency {

    private long rate;

    private String dateTime;

    private String base;

    private String target;


    @Override
    public long getRate() {
        return rate;
    }

    @Override
    public void setRate(long rate) {
        this.rate = rate;
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String getBase() {
        return base;
    }

    @Override
    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        ToStringCreator toStringCreator = new ToStringCreator(this);
        toStringCreator
                .append(getDateTime())
                .append(getBase())
                .append(getTarget())
                .append(getRate());
        return toStringCreator.toString();
    }
}
