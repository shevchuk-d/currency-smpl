package com.github.shevchuk.d.currency_new.service;

import com.github.shevchuk.d.chart.model.Chart;
import com.github.shevchuk.d.currency_new.model.Currency;

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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Created by dmsh0216 on 11/01/2017.
 */
@Service
public class CurrencyRESTGetter {
    @Autowired
    public Chart chart;

    @Autowired
    public Chart table;

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


    public void readJsonForParameters(String url, DateTime fromDate, DateTime toDate, String baseCurrency, String targetCurrency) throws IOException {
        ArrayList<JSONObject> currencyFromDateForBase = new ArrayList<>();

        ArrayList<JSONObject> currencyFromDateForBaseForTable = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            currencyFromDateForBaseForTable
                    .add(readJsonFromUrl(url + new DateTime().minusDays(10).plusDays(i).toString(DateTimeFormat.forPattern("yyyy-MM-dd")) + "?base=" + baseCurrency));
        }
        table.setCoordinates(new ArrayList<>());

        int days = Days.daysBetween(fromDate, toDate).getDays() + 1;
        int omitedDeys = days < 100 ? 0 : days/100;
        for (int i = 1; i <= days; i++){
            log.info(fromDate.plusDays(i).toString(DateTimeFormat.forPattern("yyyy-MM-dd")));
            log.info("Days to omit: " + omitedDeys);
            currencyFromDateForBase
                    .add(readJsonFromUrl(url + fromDate.plusDays(i).toString(DateTimeFormat.forPattern("yyyy-MM-dd")) + "?base=" + baseCurrency));
            i += omitedDeys;
        }
        chart.setCoordinates(new ArrayList<>());

        chart = parseToArrays(chart, currencyFromDateForBase, baseCurrency, targetCurrency);
        table = parseToArrays(table, currencyFromDateForBaseForTable, baseCurrency, targetCurrency);

    }


    public Chart parseToArrays(Chart chart, ArrayList<JSONObject> currencyFromDateForBase, String baseCurrency, String targetCurrency) {
        currencyFromDateForBase.forEach(j -> parseJsonAsCurrencies(j)
                .forEach(currencyObject -> {
                    if (baseCurrency.equals(currencyObject.getBase()) &&
                            targetCurrency.equals(currencyObject.getTarget())
                            ) {
                        log.info(String.valueOf(currencyObject.getDateTime()));
                        log.info(String.valueOf(currencyObject.getBase()));
                        log.info(String.valueOf(currencyObject.getTarget()));
                        ArrayList<String> arrayList = new ArrayList<>();

                        DateTime jodaTime = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(currencyObject.getDateTime());
                        int i = 1;
                        String newDate = "new Date("
                                + jodaTime.getYear() + ", "
                                + (jodaTime.getMonthOfYear() - i) + ", "
                                + jodaTime.getDayOfMonth() + ")";
                        if (chart.getCoordinates().size() >= 1 && chart.getCoordinates().get(chart.getCoordinates().size() - 1).get(0).equals(newDate) ){
                            newDate = "new Date("
                                    + jodaTime.getYear() + ", "
                                    + (jodaTime.getMonthOfYear() - i) + ", "
                                    + (jodaTime.getDayOfMonth() + 1) + ")";
                        }
                        if (chart.getCoordinates().size() >= 2 && chart.getCoordinates().get(chart.getCoordinates().size() - 2).get(0).equals(newDate) ){
                            newDate = "new Date("
                                    + jodaTime.getYear() + ", "
                                    + (jodaTime.getMonthOfYear() - i) + ", "
                                    + (jodaTime.getDayOfMonth() + 2) + ")";
                        }
                        arrayList.add(newDate);
                        DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                        decimalFormat.setMaximumFractionDigits(6);
                        arrayList.add(String.valueOf(
                                ( decimalFormat.format(((float)currencyObject.getRate() ) /  Float.parseFloat(currencyMultiplier))
//                    new BigDecimal( ((float)currencyObject.getRate() ) /  Float.parseFloat(currencyMultiplier) ).toPlainString()
                                )));
                        chart.getCoordinates().add(arrayList);
                        log.info(chart.getCoordinates().toString());
                    }
                }));
        return chart;
    }
}
