package com.github.shevchuk.d.currency.service;

import com.github.shevchuk.d.currency.model.Currency;
import com.github.shevchuk.d.currency.model.CurrencyDTO;
import com.github.shevchuk.d.currency.model.CurrencyEntity;
import org.joda.time.DateTime;
import org.joda.time.Days;

import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CurrencyServiceImpl currencyServiceImpl;

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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

    public static CurrencyDTO parseJsonAsCurrency(JSONObject jsonObject) {
        CurrencyDTO currency = new CurrencyDTO();
        currency.setDateTime(DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(String.valueOf(jsonObject.get("date"))));
        currency.setBase(String.valueOf(jsonObject.get("base")));
        currency.setRatesHM(
                arrayToHashMap(String.valueOf(jsonObject.get("rates")).split(","))
        );
        return currency;
    }

    private static HashMap<String, BigInteger> arrayToHashMap(String[] array){
        HashMap<String, BigInteger> hashMap = new HashMap<>();
        Arrays.stream(array).forEach(item -> {
            String[] entry = item.replace("\"", "").replace("}", "").replace("{", "").split(":");
            hashMap.put(entry[0], BigInteger.valueOf( Math.round((Float.parseFloat(entry[1]) * 100000) )));
        });
        return hashMap;
    }


    public void readJsonForParameters(String url, DateTime from, String base, String[] bases) throws IOException {
        ArrayList<JSONObject> currencyFromDateForBase = new ArrayList<>();
        ArrayList<ArrayList<String>> currencyForChart = new ArrayList<>();
        DateTime today = new DateTime();
        int days = Days.daysBetween(from, today).getDays();
        for (int i = 0; i < days; i++){
            Currency currency = parseJsonAsCurrency(readJsonFromUrl(url + from.plusDays(i).toString(DateTimeFormat.forPattern("yyyy-MM-dd")) + "?base=" + base));
            ArrayList<String> chartItem = new ArrayList<>();
            chartItem.add(currency.getDateTime().toString(DateTimeFormat.forPattern("yyyyMMdd")));
            Arrays.stream(bases).forEach(b -> {
                chartItem.add(String.valueOf(currency.getRatesHM().get(b)));
            });
            currencyForChart.add(chartItem);
            currencyFromDateForBase.add(readJsonFromUrl(url + from.plusDays(i).toString(DateTimeFormat.forPattern("yyyy-MM-dd")) + "?base=" + base));
            CurrencyEntity currencyEntity = new CurrencyEntity();
            currencyEntity.setBase(currency.getBase());
            currencyEntity.setRatesHM(currency.getRatesHM());
            currencyEntity.setDateTime(currency.getDateTime());
            log.info(currencyEntity.toString());
            currencyServiceImpl.save(currencyEntity);

        }
        currencyForChart.forEach( ch -> {
            System.out.print(ch);
            System.out.println(",");
        });
    }

    public static void main(String[] args) throws IOException, JSONException {
        new CurrencyRESTGetter().readJsonForParameters("http://api.fixer.io/", new DateTime("2016-11-11"), "USD", new String[]{"EUR"});
    }

}
