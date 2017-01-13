package com.github.shevchuk.d.currency.model;

import com.github.shevchuk.d.utils.ToStringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by dmsh0216 on 11/01/2017.
 */
@Entity
@Table(name = "currency")
public class CurrencyEntity implements Currency{
    @Id
    @Column(name = "date")
    private String date;
    @Transient
    private DateTime dateTime;
    @Column
    private String base;
    @Column
    private String rates;
    @Transient
    private HashMap<String, BigInteger> ratesHM;

    public CurrencyEntity(){
        this.dateTime = new DateTime();
        this.base = "";
        this.date = "";
        this.ratesHM = new HashMap<>();
        this.rates = this.ratesHM.toString();
    }

    public DateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
        this.date = dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
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
        this.rates = ToStringUtils.hashMapToString(ratesHM);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\t\"dateTime\": \"" + getDateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd")) + "\",\n");
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
