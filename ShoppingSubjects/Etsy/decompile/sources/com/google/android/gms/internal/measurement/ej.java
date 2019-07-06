package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.en;

public final class ej<T extends Context & en> {
    private final T a;

    public ej(T t) {
        Preconditions.checkNotNull(t);
        this.a = t;
    }

    private final void a(Runnable runnable) {
        ey a2 = ey.a((Context) this.a);
        a2.q().a((Runnable) new em(this, a2, runnable));
    }

    private final aq c() {
        return bu.a(this.a, null, null).r();
    }

    @MainThread
    public final int a(Intent intent, int i, int i2) {
        bu a2 = bu.a(this.a, null, null);
        aq r = a2.r();
        if (intent == null) {
            r.i().a("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        a2.u();
        r.w().a("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            a((Runnable) new ek(this, i2, r, intent));
        }
        return 2;
    }

    @MainThread
    public final IBinder a(Intent intent) {
        if (intent == null) {
            c().h_().a("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgp(ey.a((Context) this.a));
        }
        c().i().a("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final void a() {
        bu a2 = bu.a(this.a, null, null);
        aq r = a2.r();
        a2.u();
        r.w().a("Local AppMeasurementService is starting up");
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(int i, aq aqVar, Intent intent) {
        if (((en) this.a).callServiceStopSelfResult(i)) {
            aqVar.w().a("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            c().w().a("Completed wakeful intent.");
            ((en) this.a).zzb(intent);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(aq aqVar, JobParameters jobParameters) {
        aqVar.w().a("AppMeasurementJobService processed last upload request.");
        ((en) this.a).zza(jobParameters, false);
    }

    @TargetApi(24)
    @MainThread
    public final boolean a(JobParameters jobParameters) {
        bu a2 = bu.a(this.a, null, null);
        aq r = a2.r();
        String string = jobParameters.getExtras().getString(ResponseConstants.ACTION);
        a2.u();
        r.w().a("Local AppMeasurementJobService called. action", string);
        if ("com.google.android.gms.measurement.UPLOAD".equals(string)) {
            a((Runnable) new el(this, r, jobParameters));
        }
        return true;
    }

    @MainThread
    public final void b() {
        bu a2 = bu.a(this.a, null, null);
        aq r = a2.r();
        a2.u();
        r.w().a("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final boolean b(Intent intent) {
        if (intent == null) {
            c().h_().a("onUnbind called with null intent");
            return true;
        }
        c().w().a("onUnbind called for intent. action", intent.getAction());
        return true;
    }

    @MainThread
    public final void c(Intent intent) {
        if (intent == null) {
            c().h_().a("onRebind called with null intent");
            return;
        }
        c().w().a("onRebind called. action", intent.getAction());
    }
}
