package com.example.coffeemania.misc;

public class Constants {
    public static final int UPDATE_INTERVAL_IN_MILLISECONDS = 1 * 60 * 1000;
    public static final int FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private static final String URL_BASE = "https://api.foursquare.com/v2/venues/search?";
    private static final String CLIENT_ID = "ACAO2JPKM1MXHQJCK45IIFKRFR2ZVL0QASMCBCG5NPJQWF2G";
    private static final String CLIENT_SECRET = "YZCKUYJ1WHUV2QICBXUBEILZI1DMPUIDP5SHV043O04FKBHL";
    private static final String QUERY = "coffee";
    public static final String URL = URL_BASE + "client_id=" + CLIENT_ID +
            "&client_secret=" + CLIENT_SECRET + "&v=20140806&m=swarm&query=" + QUERY;
}