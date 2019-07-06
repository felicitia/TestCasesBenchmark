package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;

public final class zzai extends zzar {
    /* access modifiers changed from: private */
    public final zzbf zzve;

    public zzai(zzat zzat, zzav zzav) {
        super(zzat);
        Preconditions.checkNotNull(zzav);
        this.zzve = new zzbf(zzat, zzav);
    }

    /* access modifiers changed from: 0000 */
    public final void onServiceConnected() {
        zzk.zzab();
        this.zzve.onServiceConnected();
    }

    public final void setLocalDispatchPeriod(int i) {
        zzch();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zzbw().zza((Runnable) new zzaj(this, i));
    }

    public final void start() {
        this.zzve.start();
    }

    public final long zza(zzaw zzaw) {
        zzch();
        Preconditions.checkNotNull(zzaw);
        zzk.zzab();
        long zza = this.zzve.zza(zzaw, true);
        if (zza == 0) {
            this.zzve.zzb(zzaw);
        }
        return zza;
    }

    public final void zza(zzca zzca) {
        zzch();
        zzbw().zza((Runnable) new zzao(this, zzca));
    }

    public final void zza(zzch zzch) {
        Preconditions.checkNotNull(zzch);
        zzch();
        zzb("Hit delivery requested", zzch);
        zzbw().zza((Runnable) new zzam(this, zzch));
    }

    public final void zza(String str, Runnable runnable) {
        Preconditions.checkNotEmpty(str, "campaign param can't be empty");
        zzbw().zza((Runnable) new zzal(this, str, runnable));
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        this.zzve.zzm();
    }

    public final void zzbo() {
        zzch();
        Context context = getContext();
        if (!zzct.zza(context) || !zzcu.zze(context)) {
            zza((zzca) null);
            return;
        }
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
        context.startService(intent);
    }

    public final void zzbq() {
        zzch();
        zzk.zzab();
        zzbf zzbf = this.zzve;
        zzk.zzab();
        zzbf.zzch();
        zzbf.zzq("Service disconnected");
    }

    /* access modifiers changed from: 0000 */
    public final void zzbr() {
        zzk.zzab();
        this.zzve.zzbr();
    }
}
