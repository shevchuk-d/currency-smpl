package com.github.shevchuk.d.chart.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by dmsh0216 on 16/01/2017.
 */
public class Chart {
    public static Logger log = LoggerFactory.getLogger(Chart.class);
    private ArrayList<ArrayList<String>> coordinates;
    private String toStringed;

    public Chart(){
        this.coordinates = new ArrayList<>();
        this.toStringed = "";
    }

    public ArrayList<ArrayList<String>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<ArrayList<String>> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        log.info(this.coordinates.toString());
        this.coordinates.forEach(strings -> {
            stringBuilder.append("[");
            strings.forEach(s -> {
                stringBuilder.append(s);
                stringBuilder.append(", ");
            });
            stringBuilder.append("]");
            stringBuilder.delete(stringBuilder.lastIndexOf(","), stringBuilder.lastIndexOf(",") + 2);
            stringBuilder.append(", ");
        });

        try {
            toStringed = stringBuilder.delete(stringBuilder.lastIndexOf(","), stringBuilder.lastIndexOf(",") + 2).toString();
        }catch (Exception e){
            log.debug(stringBuilder.toString());
            throw e;
        }
        return toStringed;
    }

    public String getToStringed() {
        return toStringed;
    }

    public void setToStringed(String toStringed) {
        this.toStringed = toStringed;
    }
}
