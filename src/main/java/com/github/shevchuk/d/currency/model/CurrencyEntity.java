package com.github.shevchuk.d.currency.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by dmsh0216 on 11/01/2017.
 */
@Entity
@Table(name = "currency")
public class CurrencyEntity implements Currency{
    @Id
    @Column
    private DateTime date;
    @Column
    private String base;
    @Column
    private String rates;
    @Transient
    private HashMap<String, BigInteger> ratesHM;

    public CurrencyEntity(){
        this.date = new DateTime();
        this.base = "";
        this.ratesHM = new HashMap<>();
        this.rates = this.ratesHM.toString();
    }

    @Override
    public DateTime getDate() {
        return date;
    }
    @Override
    public void setDate(DateTime date) {
        this.date = date;
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
    public HashMap<String, BigInteger> getRatesHM() {
        return ratesHM;
    }
    @Override
    public void setRatesHM(HashMap<String, BigInteger> ratesHM) {
        this.ratesHM = ratesHM;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\t\"date\": \"" + getDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd")) + "\",\n");
        sb.append("\t\"base\": \"" + getBase() + "\",\n");
        sb.append("\t\"rates\": {\n");
        ratesHM.forEach((key, value) -> sb.append("\t\t\"" + key + "\": " + ( Float.parseFloat(String.valueOf(value))/10000 ) + ",\n"));
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf("\n"));
        sb.append("\t}\n");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String getRates() {
        return rates;
    }

    @Override
    public void setRates(String rates) {
        this.rates = rates;
    }
}
