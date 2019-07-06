package com.google.android.gms.internal.measurement;

final class zzcx implements zzca {
    private final /* synthetic */ Runnable zzabs;
    private final /* synthetic */ zzcu zzabt;

    zzcx(zzcu zzcu, Runnable runnable) {
        this.zzabt = zzcu;
        this.zzabs = runnable;
    }

    public final void zza(Throwable th) {
        this.zzabt.handler.post(this.zzabs);
    }
}
