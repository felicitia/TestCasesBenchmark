package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.Collections;

public final class zzax extends zzar {
    /* access modifiers changed from: private */
    public final zzaz zzwk = new zzaz(this);
    private zzci zzwl;
    private final zzbw zzwm;
    private final zzcz zzwn;

    protected zzax(zzat zzat) {
        super(zzat);
        this.zzwn = new zzcz(zzat.zzbt());
        this.zzwm = new zzay(this, zzat);
    }

    /* access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        zzk.zzab();
        if (this.zzwl != null) {
            this.zzwl = null;
            zza("Disconnected from device AnalyticsService", componentName);
            zzby().zzbq();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzci zzci) {
        zzk.zzab();
        this.zzwl = zzci;
        zzcu();
        zzby().onServiceConnected();
    }

    private final void zzcu() {
        this.zzwn.start();
        this.zzwm.zzh(((Long) zzcc.zzzt.get()).longValue());
    }

    /* access modifiers changed from: private */
    public final void zzcv() {
        zzk.zzab();
        if (isConnected()) {
            zzq("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public final boolean connect() {
        zzk.zzab();
        zzch();
        if (this.zzwl != null) {
            return true;
        }
        zzci zzcw = this.zzwk.zzcw();
        if (zzcw == null) {
            return false;
        }
        this.zzwl = zzcw;
        zzcu();
        return true;
    }

    public final void disconnect() {
        zzk.zzab();
        zzch();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzwk);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        if (this.zzwl != null) {
            this.zzwl = null;
            zzby().zzbq();
        }
    }

    public final boolean isConnected() {
        zzk.zzab();
        zzch();
        return this.zzwl != null;
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
    }

    public final boolean zzb(zzch zzch) {
        Preconditions.checkNotNull(zzch);
        zzk.zzab();
        zzch();
        zzci zzci = this.zzwl;
        if (zzci == null) {
            return false;
        }
        try {
            zzci.zza(zzch.zzcs(), zzch.zzen(), zzch.zzep() ? zzbu.zzdz() : zzbu.zzea(), Collections.emptyList());
            zzcu();
            return true;
        } catch (RemoteException unused) {
            zzq("Failed to send hits to AnalyticsService");
            return false;
        }
    }
}
