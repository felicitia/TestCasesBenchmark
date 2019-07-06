package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public abstract class zzgg {
    protected volatile int zztb = -1;

    public abstract zzgg zza(zzfx zzfx) throws IOException;

    public void zza(zzfy zzfy) throws IOException {
    }

    /* access modifiers changed from: protected */
    public int zzax() {
        return 0;
    }

    public final int zzdg() {
        int zzax = zzax();
        this.zztb = zzax;
        return zzax;
    }

    public static final byte[] zzb(zzgg zzgg) {
        byte[] bArr = new byte[zzgg.zzdg()];
        try {
            zzfy zzk = zzfy.zzk(bArr, 0, bArr.length);
            zzgg.zza(zzk);
            zzk.zzgf();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzgg> T zza(T t, byte[] bArr) throws zzgf {
        return zza(t, bArr, 0, bArr.length);
    }

    private static final <T extends zzgg> T zza(T t, byte[] bArr, int i, int i2) throws zzgf {
        try {
            zzfx zzj = zzfx.zzj(bArr, 0, i2);
            t.zza(zzj);
            zzj.zzl(0);
            return t;
        } catch (zzgf e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public String toString() {
        return zzgh.zzc(this);
    }

    /* renamed from: zzgg */
    public zzgg clone() throws CloneNotSupportedException {
        return (zzgg) super.clone();
    }
}
