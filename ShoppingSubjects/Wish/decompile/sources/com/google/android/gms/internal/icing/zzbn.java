package com.google.android.gms.internal.icing;

final class zzbn {
    private final byte[] buffer;
    private final zzbu zzdw;

    private zzbn(int i) {
        this.buffer = new byte[i];
        this.zzdw = zzbu.zzb(this.buffer);
    }

    /* synthetic */ zzbn(int i, zzbj zzbj) {
        this(i);
    }

    public final zzbi zzy() {
        if (this.zzdw.zzab() == 0) {
            return new zzbp(this.buffer);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzbu zzz() {
        return this.zzdw;
    }
}
