package com.github.shevchuk.d.view.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


/**
 * Created by dmsh0216 on 17/01/2017.
 */
public class CurrencyCurrentView {
    private String fromTime;

    private String toTime;

    private String base;

    private String target;

    private String period;

    private String amount;

    private String rate;

    private String result;

    public CurrencyCurrentView(){
        this.period = "7";
        this.fromTime = new DateTime().minusDays(Integer.parseInt(this.period)).toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
        this.toTime = new DateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
        this.base = "USD";
        this.target = "EUR";
        this.amount = "0";
        this.rate = "0";
        this.result = String.valueOf(Double.parseDouble( this.amount ) * Double.parseDouble( this.rate));
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getResult() {
        return String.valueOf(Double.parseDouble( this.amount ) * Double.parseDouble( this.rate));
    }

    public void setResult(String result) {
        this.result = result;
    }
}
