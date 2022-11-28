package com.kokn.paperround.constants;

public class Constants {

    public final static String HOST_URL;
    private final static String HOST_URL_LOCAL = "http://localhost:8100";
    private final static String HOST_URL_DEV = "https://dev-paperrouter.com";
    private final static String HOST_URL_PROD = "https://paperrouter.com";

    static {
        if (System.getProperty("ENC_MODE") == null)
            System.setProperty("ENC_MODE", "DEV");
        switch (System.getProperty("ENC_MODE")) {
            case "PROD": HOST_URL = HOST_URL_PROD; break;
            case "LOCAL": HOST_URL = HOST_URL_LOCAL; break;
            default: HOST_URL = HOST_URL_DEV; break;
        }
    }

}
