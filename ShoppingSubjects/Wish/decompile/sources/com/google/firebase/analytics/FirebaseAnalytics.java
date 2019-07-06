package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.tasks.Task;

@Keep
public final class FirebaseAnalytics {
    private final zzgn zzacv;

    public static class Event {
    }

    public static class Param {
    }

    public static class UserProperty {
    }

    public FirebaseAnalytics(zzgn zzgn) {
        Preconditions.checkNotNull(zzgn);
        this.zzacv = zzgn;
    }

    @Keep
    public static FirebaseAnalytics getInstance(Context context) {
        return zzgn.zza(context, null, null).zzkc();
    }

    public final Task<String> getAppInstanceId() {
        return this.zzacv.zzfy().getAppInstanceId();
    }

    public final void logEvent(String str, Bundle bundle) {
        this.zzacv.zzkb().logEvent(str, bundle);
    }

    public final void resetAnalyticsData() {
        this.zzacv.zzfy().resetAnalyticsData();
    }

    public final void setAnalyticsCollectionEnabled(boolean z) {
        this.zzacv.zzkb().setMeasurementEnabled(z);
    }

    @Keep
    public final void setCurrentScreen(Activity activity, String str, String str2) {
        this.zzacv.zzgb().setCurrentScreen(activity, str, str2);
    }

    public final void setMinimumSessionDuration(long j) {
        this.zzacv.zzkb().setMinimumSessionDuration(j);
    }

    public final void setSessionTimeoutDuration(long j) {
        this.zzacv.zzkb().setSessionTimeoutDuration(j);
    }

    public final void setUserId(String str) {
        this.zzacv.zzkb().setUserPropertyInternal("app", "_id", str);
    }

    public final void setUserProperty(String str, String str2) {
        this.zzacv.zzkb().setUserProperty(str, str2);
    }
}
