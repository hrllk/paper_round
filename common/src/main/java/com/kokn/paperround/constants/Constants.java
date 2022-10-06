package com.kokn.paperround.constants;

import org.apache.commons.lang3.StringUtils;

public class Constants {

    public final static String SIGNUP_CONFIRM_URL;
    private final static String SIGNUP_CONFIRM_URL_DEBUG = "https://dev-paperrouter.com";
    private final static String SIGNUP_CONFIRM_URL_PROD = "https://paperrouter.com";

    static {
        SIGNUP_CONFIRM_URL = StringUtils.equalsIgnoreCase(System.getProperty("ENC_MODE"), "PROD") ? SIGNUP_CONFIRM_URL_PROD : SIGNUP_CONFIRM_URL_DEBUG;
    }

}
