package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzg {
    private final Clock clock;
    private final zzj zzrn;
    private boolean zzro;
    private long zzrp;
    private long zzrq;
    private long zzrr;
    private long zzrs;
    private long zzrt;
    private boolean zzru;
    private final Map<Class<? extends zzi>, zzi> zzrv;
    private final List<zzo> zzrw;

    private zzg(zzg zzg) {
        this.zzrn = zzg.zzrn;
        this.clock = zzg.clock;
        this.zzrp = zzg.zzrp;
        this.zzrq = zzg.zzrq;
        this.zzrr = zzg.zzrr;
        this.zzrs = zzg.zzrs;
        this.zzrt = zzg.zzrt;
        this.zzrw = new ArrayList(zzg.zzrw);
        this.zzrv = new HashMap(zzg.zzrv.size());
        for (Entry entry : zzg.zzrv.entrySet()) {
            zzi zzc = zzc((Class) entry.getKey());
            ((zzi) entry.getValue()).zzb(zzc);
            this.zzrv.put((Class) entry.getKey(), zzc);
        }
    }

    zzg(zzj zzj, Clock clock2) {
        Preconditions.checkNotNull(zzj);
        Preconditions.checkNotNull(clock2);
        this.zzrn = zzj;
        this.clock = clock2;
        this.zzrs = 1800000;
        this.zzrt = 3024000000L;
        this.zzrv = new HashMap();
        this.zzrw = new ArrayList();
    }

    @TargetApi(19)
    private static <T extends zzi> T zzc(Class<T> cls) {
        try {
            return (zzi) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            if (e instanceof InstantiationException) {
                throw new IllegalArgumentException("dataType doesn't have default constructor", e);
            } else if (e instanceof IllegalAccessException) {
                throw new IllegalArgumentException("dataType default constructor is not accessible", e);
            } else if (VERSION.SDK_INT < 19 || !(e instanceof ReflectiveOperationException)) {
                throw new RuntimeException(e);
            } else {
                throw new IllegalArgumentException("Linkage exception", e);
            }
        }
    }

    public final <T extends zzi> T zza(Class<T> cls) {
        return (zzi) this.zzrv.get(cls);
    }

    public final void zza(long j) {
        this.zzrq = j;
    }

    public final void zza(zzi zzi) {
        Preconditions.checkNotNull(zzi);
        Class cls = zzi.getClass();
        if (cls.getSuperclass() != zzi.class) {
            throw new IllegalArgumentException();
        }
        zzi.zzb(zzb(cls));
    }

    public final <T extends zzi> T zzb(Class<T> cls) {
        T t = (zzi) this.zzrv.get(cls);
        if (t != null) {
            return t;
        }
        T zzc = zzc(cls);
        this.zzrv.put(cls, zzc);
        return zzc;
    }

    public final zzg zzo() {
        return new zzg(this);
    }

    public final Collection<zzi> zzp() {
        return this.zzrv.values();
    }

    public final List<zzo> zzq() {
        return this.zzrw;
    }

    public final long zzr() {
        return this.zzrp;
    }

    public final void zzs() {
        this.zzrn.zzy().zze(this);
    }

    public final boolean zzt() {
        return this.zzro;
    }

    /* access modifiers changed from: 0000 */
    public final void zzu() {
        this.zzrr = this.clock.elapsedRealtime();
        this.zzrp = this.zzrq != 0 ? this.zzrq : this.clock.currentTimeMillis();
        this.zzro = true;
    }

    /* access modifiers changed from: 0000 */
    public final zzj zzv() {
        return this.zzrn;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzw() {
        return this.zzru;
    }

    /* access modifiers changed from: 0000 */
    public final void zzx() {
        this.zzru = true;
    }
}
