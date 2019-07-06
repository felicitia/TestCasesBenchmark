package com.google.firebase.analytics.connector;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class b implements a {
    private static volatile a c;
    @VisibleForTesting
    final Map<String, Object> a = new ConcurrentHashMap();
    private final AppMeasurement b;

    private b(AppMeasurement appMeasurement) {
        Preconditions.checkNotNull(appMeasurement);
        this.b = appMeasurement;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static a a(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new b(AppMeasurement.getInstance(context));
                }
            }
        }
        return c;
    }
}
