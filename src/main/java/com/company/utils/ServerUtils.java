package com.company.utils;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by Onlin on 29.10.2016.
 */
@Component
public class ServerUtils {
    public static final Pattern PATTERN_BY_LOGIN = Pattern.compile("^[A-Za-z]{3,}$");
    public static final Pattern PATTERN_BY_FULL_NAME = Pattern.compile("^(\\b[A-Z][a-z]{4,}\\s){2}\\b[A-Z][a-z]{4,}$");
    public static final Pattern PATTERN_BY_EMAIL = Pattern.compile("\\A[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
            "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z");
    public static final Pattern PATTERN_BY_NAME = Pattern.compile("^\\b[A-Z][a-z]{4,}$");
    public static final Pattern PATTERN_PHONE_NAMBER = Pattern.compile("^((8|\\+38)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
    public static final Pattern PATTERN_HOME_NAMBER = Pattern.compile("[0-9]{6}");


    public boolean checkParameter(String parameter, Pattern pattern, boolean notNull) {
        boolean check = true;
        if (notNull) {
            if (parameter == null || parameter.equals("")) {
                check = false;
            }else {

            }
        }
        return check;
    }


}
