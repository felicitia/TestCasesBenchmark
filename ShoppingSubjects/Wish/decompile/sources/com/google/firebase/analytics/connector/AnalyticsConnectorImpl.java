package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.internal.zzc;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzbqi;
    private final AppMeasurement zzbqh;
    final Map<String, Object> zzbqj = new ConcurrentHashMap();

    private AnalyticsConnectorImpl(AppMeasurement appMeasurement) {
        Preconditions.checkNotNull(appMeasurement);
        this.zzbqh = appMeasurement;
    }

    public static AnalyticsConnector getInstance(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzbqi == null) {
            synchronized (AnalyticsConnector.class) {
                if (zzbqi == null) {
                    zzbqi = new AnalyticsConnectorImpl(AppMeasurement.getInstance(context));
                }
            }
        }
        return zzbqi;
    }

    public void logEvent(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (zzc.zzff(str) && zzc.zza(str2, bundle) && zzc.zzb(str, str2, bundle)) {
            this.zzbqh.logEventInternal(str, str2, bundle);
        }
    }

    public void setUserProperty(String str, String str2, Object obj) {
        if (zzc.zzff(str) && zzc.zzw(str, str2)) {
            this.zzbqh.setUserPropertyInternal(str, str2, obj);
        }
    }
}
