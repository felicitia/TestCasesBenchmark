package com.contextlogic.wish.util;

import java.util.Date;
import java.util.TimeZone;

public class TimezoneUtil {
    public static String getCurrentTimezone() {
        return Float.toString(((float) (TimeZone.getDefault().getOffset(new Date().getTime()) / 1000)) / 3600.0f);
    }

    public static String getCurrentTimeZoneId() {
        return TimeZone.getDefault().getID();
    }

    public static long defaultTimeZoneToUTC(long j) {
        return j - ((long) TimeZone.getDefault().getOffset(new Date().getTime()));
    }

    public static long utcToDefaultTimezone(long j) {
        return j + ((long) TimeZone.getDefault().getOffset(new Date().getTime()));
    }
}
