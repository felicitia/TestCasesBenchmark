package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.bu;
import com.google.android.gms.tasks.f;

@Keep
public final class FirebaseAnalytics {
    private final bu zzacv;

    public static class a {
    }

    public static class b {
    }

    public static class c {
    }

    public FirebaseAnalytics(bu buVar) {
        Preconditions.checkNotNull(buVar);
        this.zzacv = buVar;
    }

    @Keep
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @NonNull
    public static FirebaseAnalytics getInstance(@NonNull Context context) {
        return bu.a(context, null, null).j();
    }

    @NonNull
    public final f<String> getAppInstanceId() {
        return this.zzacv.h().G();
    }

    public final void logEvent(@Size(max = 40, min = 1) @NonNull String str, @Nullable Bundle bundle) {
        this.zzacv.i().logEvent(str, bundle);
    }

    public final void resetAnalyticsData() {
        this.zzacv.h().I();
    }

    public final void setAnalyticsCollectionEnabled(boolean z) {
        this.zzacv.i().setMeasurementEnabled(z);
    }

    @Keep
    @MainThread
    public final void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        this.zzacv.s().a(activity, str, str2);
    }

    public final void setMinimumSessionDuration(long j) {
        this.zzacv.i().setMinimumSessionDuration(j);
    }

    public final void setSessionTimeoutDuration(long j) {
        this.zzacv.i().setSessionTimeoutDuration(j);
    }

    public final void setUserId(@Nullable String str) {
        this.zzacv.i().setUserPropertyInternal("app", "_id", str);
    }

    public final void setUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable @Size(max = 36) String str2) {
        this.zzacv.i().setUserProperty(str, str2);
    }
}
