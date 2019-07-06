package com.google.android.gms.internal.icing;

public abstract class zzbr {
    private static volatile boolean zzeb = false;
    private int zzdy;
    private int zzdz;
    private boolean zzea;

    private zzbr() {
        this.zzdy = 100;
        this.zzdz = Integer.MAX_VALUE;
        this.zzea = false;
    }

    static zzbr zza(byte[] bArr, int i, int i2, boolean z) {
        zzbt zzbt = new zzbt(bArr, 0, i2, false);
        try {
            zzbt.zzk(i2);
            return zzbt;
        } catch (zzcs e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzaa();

    public abstract int zzk(int i) throws zzcs;
}
