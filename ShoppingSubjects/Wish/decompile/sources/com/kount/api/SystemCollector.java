package com.kount.api;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class SystemCollector extends CollectorTaskBase {
    private final WindowManager windowManager;

    static String internalName() {
        return "LOCAL";
    }

    /* access modifiers changed from: 0000 */
    public final String getName() {
        return "System Collector";
    }

    SystemCollector(Object obj, Context context) {
        super(obj);
        this.windowManager = (WindowManager) context.getSystemService("window");
    }

    /* access modifiers changed from: 0000 */
    public String getInternalName() {
        return internalName();
    }

    /* access modifiers changed from: 0000 */
    public void collect() {
        addDataPoint(PostKey.MOBILE_MODEL.toString(), Build.FINGERPRINT);
        addDataPoint(PostKey.OS_VERSION.toString(), VERSION.RELEASE);
        addDataPoint(PostKey.TOTAL_MEMORY.toString(), Long.toString(getTotalMemory() / 1048576));
        addDataPoint(PostKey.LANGUAGE.toString(), getLanguage());
        addDataPoint(PostKey.SCREEN_AVAILABLE.toString(), getScreenAvailable());
        addDataPoint(PostKey.TIMEZONE_AUGUST.toString(), Long.toString((long) getTimeZoneOffsetAugust()));
        addDataPoint(PostKey.TIMEZONE_FEBRUARY.toString(), Long.toString((long) getTimeZoneOffsetFebruary()));
        addDataPoint(PostKey.TIMEZONE_NOW.toString(), Long.toString((long) getTimeZoneOffset()));
        addDataPoint(PostKey.DATE_TIME.toString(), Long.toString(new Date().getTime()));
        callCompletionHandler(Boolean.valueOf(true), null);
    }

    /* access modifiers changed from: protected */
    public long getTotalMemory() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            long intValue = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024);
            bufferedReader.close();
            return intValue;
        } catch (IOException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public String getLanguage() {
        StringBuilder sb = new StringBuilder();
        sb.append(Locale.getDefault().getLanguage());
        sb.append("-");
        sb.append(Locale.getDefault().getCountry());
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String getScreenAvailable() {
        Display defaultDisplay = this.windowManager.getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        if (VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            height = displayMetrics.heightPixels;
            width = i;
        } else if (VERSION.SDK_INT >= 14) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                int intValue = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                try {
                    height = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                } catch (Exception unused) {
                }
                width = intValue;
            } catch (Exception unused2) {
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(height);
        sb.append("x");
        sb.append(width);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int getTimeZoneOffsetAugust() {
        Calendar instance = Calendar.getInstance();
        instance.set(2007, 7, 1);
        return getTimeZoneOffset(instance);
    }

    /* access modifiers changed from: protected */
    public int getTimeZoneOffsetFebruary() {
        Calendar instance = Calendar.getInstance();
        instance.set(2007, 1, 1);
        return getTimeZoneOffset(instance);
    }

    /* access modifiers changed from: protected */
    public int getTimeZoneOffset() {
        return getTimeZoneOffset(Calendar.getInstance(TimeZone.getDefault()));
    }

    /* access modifiers changed from: protected */
    public int getTimeZoneOffset(Calendar calendar) {
        return ((TimeZone.getDefault().getOffset(calendar.getTimeInMillis()) * -1) / 1000) / 60;
    }
}
