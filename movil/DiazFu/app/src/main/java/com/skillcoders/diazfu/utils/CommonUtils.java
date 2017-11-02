package com.skillcoders.diazfu.utils;

import java.text.NumberFormat;

/**
 * Created by jvier on 01/11/2017.
 */

public class CommonUtils {

    public static String showMeTheMoney(Double amount) {
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        return defaultFormat.format(amount);
    }
}
