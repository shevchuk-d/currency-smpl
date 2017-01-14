package com.github.shevchuk.d.currency_new.service;

import com.github.shevchuk.d.currency_new.model.Currency;

import com.github.shevchuk.d.currency_new.model.CurrencyEntity;
import com.github.shevchuk.d.currency_new.model.CurrencyObject;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by dmsh0216 on 11/01/2017.
 */
@Service
public class CurrencyRESTGetter {
    public static Logger log = LoggerFactory.getLogger(CurrencyRESTGetter.class);

    @Autowired
    private  CurrencyServiceImpl currencyServiceImpl;

    @Value("${currency.multiplier}")
    private String currencyMultiplier;


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private  ArrayList<Currency> parseJsonAsCurrencies(JSONObject jsonObject) {
        ArrayList<Currency> currencies = new ArrayList<>();
        JSONArray jsonObjects = jsonObject.getJSONObject("rates").names();

        for (int i = 0; i < jsonObjects.length(); i++) {
            Currency currency = new CurrencyObject();
            currency.setDateTime(
                    DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(String.valueOf(jsonObject.get("date"))).toString("yyyy-MM-dd")
            );
            currency.setBase(String.valueOf(jsonObject.get("base")));
            currency.setTarget(jsonObjects.getString(i));
            currency.setRate(
                    Math.round(
                            jsonObject.getJSONObject("rates").getDouble(jsonObjects.getString(i)) * Integer.parseInt(currencyMultiplier)
                    )
            );
            currencies.add(currency);
        }
        return currencies;
    }

    private static HashMap<String, BigInteger> arrayToHashMap(String[] array){
        HashMap<String, BigInteger> hashMap = new HashMap<>();
        Arrays.stream(array).forEach(item -> {
            String[] entry = item.replace("\"", "").replace("}", "").replace("{", "").split(":");
            hashMap.put(entry[0], BigInteger.valueOf( Math.round((Float.parseFloat(entry[1]) * 100000) )));
        });
        return hashMap;
    }


    public  void readJsonForParameters(String url, DateTime from, String base) throws IOException {
        ArrayList<JSONObject> currencyFromDateForBase = new ArrayList<>();
        DateTime today = new DateTime();
        int days = Days.daysBetween(from, today).getDays();
        for (int i = 0; i < days; i++){
            currencyFromDateForBase
                    .add(readJsonFromUrl(url + from.plusDays(i).toString(DateTimeFormat.forPattern("yyyy-MM-dd")) + "?base=" + base));
        }
        currencyFromDateForBase.forEach(j -> {
            parseJsonAsCurrencies(j).forEach(currencyObject -> {
                log.info(String.valueOf(currencyObject.getDateTime()));
                currencyServiceImpl.save(new CurrencyEntity(currencyObject));
            });
        });
    }

//    public static void main(String[] args) throws IOException, JSONException {
//
//        readJsonForParameters("http://api.fixer.io/", new DateTime("2016-11-11"), "USD", new String[]{"EUR"});
//        currencyServiceImpl.save(currencyEntity);
//        currencyServiceImpl.save(currencyEntity);
//
//    }

}
