package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public abstract class zzbo {
    int zzhx;
    int zzhy;
    private int zzhz;
    zzbr zzia;
    private boolean zzib;

    public static zzbo zzd(byte[] bArr, int i, int i2) {
        return zza(bArr, i, i2, false);
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract <T extends zzdt> T zza(zzed<T> zzed, zzbz zzbz) throws IOException;

    public abstract int zzbs() throws IOException;

    public abstract long zzbt() throws IOException;

    public abstract long zzbu() throws IOException;

    public abstract int zzbv() throws IOException;

    public abstract long zzbw() throws IOException;

    public abstract int zzbx() throws IOException;

    public abstract boolean zzby() throws IOException;

    public abstract String zzbz() throws IOException;

    public abstract zzbd zzca() throws IOException;

    public abstract int zzcb() throws IOException;

    public abstract int zzcc() throws IOException;

    public abstract int zzcd() throws IOException;

    public abstract long zzce() throws IOException;

    public abstract int zzcf() throws IOException;

    public abstract long zzcg() throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract long zzch() throws IOException;

    public abstract boolean zzci() throws IOException;

    public abstract int zzcj();

    public abstract void zzl(int i) throws zzct;

    public abstract boolean zzm(int i) throws IOException;

    public abstract int zzo(int i) throws zzct;

    public abstract void zzp(int i);

    public abstract void zzq(int i) throws IOException;

    static zzbo zza(byte[] bArr, int i, int i2, boolean z) {
        zzbq zzbq = new zzbq(bArr, i, i2, false);
        try {
            zzbq.zzo(i2);
            return zzbq;
        } catch (zzct e) {
            throw new IllegalArgumentException(e);
        }
    }

    private zzbo() {
        this.zzhy = 100;
        this.zzhz = Integer.MAX_VALUE;
        this.zzib = false;
    }

    public final int zzn(int i) {
        if (i < 0) {
            StringBuilder sb = new StringBuilder(47);
            sb.append("Recursion limit cannot be negative: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        int i2 = this.zzhy;
        this.zzhy = i;
        return i2;
    }
}
