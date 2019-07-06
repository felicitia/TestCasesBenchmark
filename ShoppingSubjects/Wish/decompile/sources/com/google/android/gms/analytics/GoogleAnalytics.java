package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.internal.measurement.zzat;
import com.google.android.gms.internal.measurement.zzde;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzra = new ArrayList();
    private boolean zzrb;
    private Set<Object> zzrc = new HashSet();
    private boolean zzre;
    private volatile boolean zzrf;

    public GoogleAnalytics(zzat zzat) {
        super(zzat);
    }

    public static GoogleAnalytics getInstance(Context context) {
        return zzat.zzc(context).zzck();
    }

    public static void zzn() {
        synchronized (GoogleAnalytics.class) {
            if (zzra != null) {
                for (Runnable run : zzra) {
                    run.run();
                }
                zzra = null;
            }
        }
    }

    public final boolean getAppOptOut() {
        return this.zzrf;
    }

    public final boolean isDryRunEnabled() {
        return this.zzre;
    }

    public final boolean isInitialized() {
        return this.zzrb;
    }

    public final Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzh(), str, null);
            tracker.zzm();
        }
        return tracker;
    }

    public final void setDryRun(boolean z) {
        this.zzre = z;
    }

    public final void setLocalDispatchPeriod(int i) {
        zzh().zzby().setLocalDispatchPeriod(i);
    }

    public final void zzm() {
        zzde zzca = zzh().zzca();
        zzca.zzfn();
        if (zzca.zzfo()) {
            setDryRun(zzca.zzfp());
        }
        zzca.zzfn();
        this.zzrb = true;
    }
}
