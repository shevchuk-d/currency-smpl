package com.github.shevchuk.d.currency_new.model;


import javax.persistence.*;


/**
 * Created by dmsh0216 on 13/01/2017.
 */
@Entity
@Table(name = "new_currency")
public class CurrencyEntity implements Currency {

    @EmbeddedId
    private CurrencyEntityPK newCurrencyEntityPK;

    @Column(name = "rate")
    private long rate;

    @Transient
    private String dateTime;

    @Transient
    private String base;

    @Transient
    private String target;


    public CurrencyEntityPK getNewCurrencyEntityPK() {
        return newCurrencyEntityPK;
    }

    public void setNewCurrencyEntityPK(CurrencyEntityPK newCurrencyEntityPK) {
        this.newCurrencyEntityPK = newCurrencyEntityPK;
    }

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
        return this.newCurrencyEntityPK.getDateTime();
    }

    @Override
    public void setDateTime(String dateTime) {
        this.newCurrencyEntityPK.setDateTime(dateTime);
    }

    @Override
    public String getBase() {
        return this.newCurrencyEntityPK.getBase();
    }

    @Override
    public void setBase(String base) {
        this.newCurrencyEntityPK.setDateTime(dateTime);
    }

    @Override
    public String getTarget() {
        return this.newCurrencyEntityPK.getTarget();
    }

    @Override
    public void setTarget(String target) {
        this.newCurrencyEntityPK.setDateTime(dateTime);
    }
}
