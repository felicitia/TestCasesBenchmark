package com.google.android.gms.internal.firebase-perf;

import android.util.Log;
import java.util.Map;
import java.util.concurrent.TimeUnit;

final class zzv {
    private static final long zzcp = TimeUnit.MINUTES.toMicros(1);
    private final zzx zzak;
    private final boolean zzce;
    private long zzcq;
    private long zzcr;
    private zzaa zzcs = new zzaa();
    private long zzct;
    private long zzcu;
    private long zzcv;
    private long zzcw;
    private long zzcx;

    zzv(long j, long j2, zzx zzx, Map<String, Long> map, zzu zzu, boolean z) {
        long j3 = j2;
        Map<String, Long> map2 = map;
        this.zzak = zzx;
        this.zzcq = j3;
        this.zzcr = j;
        this.zzct = j3;
        long zzw = (long) zzu.zzw();
        long zzx2 = (long) zzu.zzx();
        long zzy = (long) zzu.zzy();
        long zzz = (long) zzu.zzz();
        if (map2.containsKey(zzu.zzaa())) {
            zzw = ((Long) map2.get(zzu.zzaa())).longValue();
            if (zzw == 0) {
                zzw = (long) zzu.zzw();
            }
        }
        if (map2.containsKey(zzu.zzab())) {
            zzx2 = ((Long) map2.get(zzu.zzab())).longValue();
        }
        this.zzcu = zzx2 / zzw;
        this.zzcv = zzx2;
        if (!(this.zzcv == ((long) zzu.zzx()) && this.zzcu == ((long) (zzu.zzx() / zzu.zzw())))) {
            Log.d("FirebasePerformance", String.format("Foreground %s logging rate:%d, burst capacity:%d", new Object[]{zzu.toString(), Long.valueOf(this.zzcu), Long.valueOf(this.zzcv)}));
        }
        if (map2.containsKey(zzu.zzac())) {
            zzy = ((Long) map2.get(zzu.zzac())).longValue();
            if (zzy == 0) {
                zzy = (long) zzu.zzy();
            }
        }
        if (map2.containsKey(zzu.zzad())) {
            zzz = ((Long) map2.get(zzu.zzad())).longValue();
        }
        this.zzcw = zzz / zzy;
        this.zzcx = zzz;
        if (!(this.zzcx == ((long) zzu.zzz()) && this.zzcw == ((long) (zzu.zzz() / zzu.zzy())))) {
            Log.d("FirebasePerformance", String.format("Background %s logging rate:%d, capacity:%d", new Object[]{zzu.toString(), Long.valueOf(this.zzcw), Long.valueOf(this.zzcx)}));
        }
        this.zzce = z;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized boolean zzb(zzar zzar) {
        zzaa zzaa = new zzaa();
        this.zzct = Math.min(this.zzct + Math.max(0, (this.zzcs.zza(zzaa) * this.zzcr) / zzcp), this.zzcq);
        if (this.zzct > 0) {
            this.zzct--;
            this.zzcs = zzaa;
            return true;
        }
        if (this.zzce) {
            Log.w("FirebasePerformance", "Exceeded log rate limit, dropping the log.");
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void zzb(boolean z) {
        long j;
        if (z) {
            try {
                j = this.zzcu;
            } catch (Throwable th) {
                throw th;
            }
        } else {
            j = this.zzcw;
        }
        this.zzcr = j;
        this.zzcq = z ? this.zzcv : this.zzcx;
    }
}
