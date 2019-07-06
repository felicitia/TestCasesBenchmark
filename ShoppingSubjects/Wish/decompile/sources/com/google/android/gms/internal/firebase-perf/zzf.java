package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zze.zza;
import java.lang.ref.WeakReference;

public class zzf implements zza {
    private int mState;
    private zze zzaw;
    private boolean zzax;
    private WeakReference<zza> zzay;

    protected zzf() {
        this(zze.zzg());
    }

    protected zzf(zze zze) {
        this.mState = 0;
        this.zzax = false;
        this.zzaw = zze;
        this.zzay = new WeakReference<>(this);
    }

    /* access modifiers changed from: protected */
    public final void zzl() {
        if (!this.zzax) {
            this.mState = this.zzaw.zzh();
            this.zzaw.zza(this.zzay);
            this.zzax = true;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzm() {
        if (this.zzax) {
            this.zzaw.zzb(this.zzay);
            this.zzax = false;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(int i) {
        this.zzaw.zzb(1);
    }

    public void zzd(int i) {
        this.mState = i | this.mState;
    }

    public final int zzh() {
        return this.mState;
    }
}
