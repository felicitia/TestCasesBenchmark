package com.kount.api;

enum PostKey {
    LOCATION_LATITUDE("lat"),
    LOCATION_LONGITUDE("lon"),
    LOCATION_DATE("ltm"),
    SDK_VERSION("sv"),
    SDK_TYPE("st"),
    MOBILE_MODEL("mdl"),
    SOFT_ERRORS("err"),
    MERCHANT_ID("m"),
    SESSION_ID("s"),
    OS_VERSION("os"),
    DEVICE_COOKIE("dc"),
    OLD_DEVICE_COOKIE("odc"),
    ELAPSED("elapsed"),
    TOTAL_MEMORY("dmm"),
    LANGUAGE("ln"),
    SCREEN_AVAILABLE("sa"),
    DATE_TIME("e"),
    TIMEZONE_NOW("t0"),
    TIMEZONE_AUGUST("ta"),
    TIMEZONE_FEBRUARY("tf");
    
    private final String name;

    private PostKey(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
