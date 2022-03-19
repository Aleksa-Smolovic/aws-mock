package com.quantox.awsmock.utils;

import java.util.regex.Pattern;

public final class ObjectValidator {

    public final static String EMAIL_REGEX = "^(.+)@(\\S+)$";

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
