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

    public CurrencyCurrentView(){
        this.period = "7";
        this.fromTime = new DateTime().minusDays(Integer.parseInt(this.period)).toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
        this.toTime = new DateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
        this.base = "USD";
        this.target = "EUR";
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
}
