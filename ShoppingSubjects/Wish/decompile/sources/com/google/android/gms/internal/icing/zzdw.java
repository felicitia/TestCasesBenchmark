package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzdw<T> implements zzef<T> {
    private final zzdr zzkc;
    private final boolean zzkd;
    private final zzex<?, ?> zzkm;
    private final zzca<?> zzkn;

    private zzdw(zzex<?, ?> zzex, zzca<?> zzca, zzdr zzdr) {
        this.zzkm = zzex;
        this.zzkd = zzca.zze(zzdr);
        this.zzkn = zzca;
        this.zzkc = zzdr;
    }

    static <T> zzdw<T> zza(zzex<?, ?> zzex, zzca<?> zzca, zzdr zzdr) {
        return new zzdw<>(zzex, zzca, zzdr);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzkm.zzm(t).equals(this.zzkm.zzm(t2))) {
            return false;
        }
        if (this.zzkd) {
            return this.zzkn.zza((Object) t).equals(this.zzkn.zza((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzkm.zzm(t).hashCode();
        return this.zzkd ? (hashCode * 53) + this.zzkn.zza((Object) t).hashCode() : hashCode;
    }

    public final void zza(T t, zzfr zzfr) throws IOException {
        int zzap;
        Object value;
        Iterator it = this.zzkn.zza((Object) t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzcf zzcf = (zzcf) entry.getKey();
            if (zzcf.zzar() != zzfq.MESSAGE || zzcf.zzas() || zzcf.zzat()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzcx) {
                zzap = zzcf.zzap();
                value = ((zzcx) entry).zzbg().zzl();
            } else {
                zzap = zzcf.zzap();
                value = entry.getValue();
            }
            zzfr.zza(zzap, value);
        }
        zzex<?, ?> zzex = this.zzkm;
        zzex.zzc(zzex.zzm(t), zzfr);
    }

    public final void zzc(T t) {
        this.zzkm.zzc(t);
        this.zzkn.zzc(t);
    }

    public final void zzc(T t, T t2) {
        zzeh.zza(this.zzkm, t, t2);
        if (this.zzkd) {
            zzeh.zza(this.zzkn, t, t2);
        }
    }

    public final int zzj(T t) {
        zzex<?, ?> zzex = this.zzkm;
        int zzn = zzex.zzn(zzex.zzm(t)) + 0;
        return this.zzkd ? zzn + this.zzkn.zza((Object) t).zzao() : zzn;
    }

    public final boolean zzk(T t) {
        return this.zzkn.zza((Object) t).isInitialized();
    }
}
