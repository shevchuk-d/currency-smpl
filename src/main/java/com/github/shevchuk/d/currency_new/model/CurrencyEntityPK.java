package com.github.shevchuk.d.currency_new.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by dmsh0216 on 13/01/2017.
 */

@Embeddable
public class CurrencyEntityPK implements Serializable {
    @Column(name = "dateTime")
    private String dateTime;
    @Column(name = "base")
    private String base;
    @Column(name = "target")
    private String target;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
}
