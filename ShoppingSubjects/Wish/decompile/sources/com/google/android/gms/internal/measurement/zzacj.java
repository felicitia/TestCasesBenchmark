package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzacj {
    protected volatile int zzbzo = -1;

    public String toString() {
        return zzack.zzc(this);
    }

    /* access modifiers changed from: protected */
    public int zza() {
        return 0;
    }

    public void zza(zzacb zzacb) throws IOException {
    }

    public abstract zzacj zzb(zzaca zzaca) throws IOException;

    /* renamed from: zzvu */
    public zzacj clone() throws CloneNotSupportedException {
        return (zzacj) super.clone();
    }

    public final int zzwa() {
        if (this.zzbzo < 0) {
            zzwb();
        }
        return this.zzbzo;
    }

    public final int zzwb() {
        int zza = zza();
        this.zzbzo = zza;
        return zza;
    }
}
