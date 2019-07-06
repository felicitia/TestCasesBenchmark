package com.google.android.gms.internal.firebase-perf;

final class zzbk {
    private final byte[] buffer;
    private final zzbt zzhv;

    private zzbk(int i) {
        this.buffer = new byte[i];
        this.zzhv = zzbt.zzc(this.buffer);
    }

    public final zzbd zzbq() {
        if (this.zzhv.zzcs() == 0) {
            return new zzbm(this.buffer);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzbt zzbr() {
        return this.zzhv;
    }

    /* synthetic */ zzbk(int i, zzbe zzbe) {
        this(i);
    }
}
