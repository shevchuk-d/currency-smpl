package com.github.shevchuk.d.utils;

import java.util.HashMap;

/**
 * Created by dmsh0216 on 13/01/2017.
 */
public class ToStringUtils {

    public static String hashMapToString(HashMap<? , ?> hashMap) {
        StringBuilder stringed = new StringBuilder();
        hashMap.forEach( (k, v) -> stringed.append(k.toString() + "=" + v.toString() + ",") );
        return stringed.toString().substring( 0, stringed.length() );
    }
}
