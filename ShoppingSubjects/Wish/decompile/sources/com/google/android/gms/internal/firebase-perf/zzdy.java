package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzdy<T> implements zzej<T> {
    private final zzdt zzot;
    private final boolean zzou;
    private final zzfb<?, ?> zzpd;
    private final zzca<?> zzpe;

    private zzdy(zzfb<?, ?> zzfb, zzca<?> zzca, zzdt zzdt) {
        this.zzpd = zzfb;
        this.zzou = zzca.zze(zzdt);
        this.zzpe = zzca;
        this.zzot = zzdt;
    }

    static <T> zzdy<T> zza(zzfb<?, ?> zzfb, zzca<?> zzca, zzdt zzdt) {
        return new zzdy<>(zzfb, zzca, zzdt);
    }

    public final T newInstance() {
        return this.zzot.zzdr().zzdv();
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzpd.zzp(t).equals(this.zzpd.zzp(t2))) {
            return false;
        }
        if (this.zzou) {
            return this.zzpe.zza((Object) t).equals(this.zzpe.zza((Object) t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzpd.zzp(t).hashCode();
        return this.zzou ? (hashCode * 53) + this.zzpe.zza((Object) t).hashCode() : hashCode;
    }

    public final void zzc(T t, T t2) {
        zzel.zza(this.zzpd, t, t2);
        if (this.zzou) {
            zzel.zza(this.zzpe, t, t2);
        }
    }

    public final void zza(T t, zzfw zzfw) throws IOException {
        Iterator it = this.zzpe.zza((Object) t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzcf zzcf = (zzcf) entry.getKey();
            if (zzcf.zzdk() != zzfv.MESSAGE || zzcf.zzdl() || zzcf.zzdm()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzcy) {
                zzfw.zza(zzcf.zzdi(), (Object) ((zzcy) entry).zzeh().zzbe());
            } else {
                zzfw.zza(zzcf.zzdi(), entry.getValue());
            }
        }
        zzfb<?, ?> zzfb = this.zzpd;
        zzfb.zzc(zzfb.zzp(t), zzfw);
    }

    public final void zza(T t, zzei zzei, zzbz zzbz) throws IOException {
        boolean z;
        zzfb<?, ?> zzfb = this.zzpd;
        zzca<?> zzca = this.zzpe;
        Object zzq = zzfb.zzq(t);
        zzcd zzb = zzca.zzb(t);
        do {
            try {
                if (zzei.zzcq() == Integer.MAX_VALUE) {
                    zzfb.zzf(t, zzq);
                    return;
                }
                int tag = zzei.getTag();
                if (tag == 11) {
                    Object obj = null;
                    zzbd zzbd = null;
                    int i = 0;
                    while (zzei.zzcq() != Integer.MAX_VALUE) {
                        int tag2 = zzei.getTag();
                        if (tag2 == 16) {
                            i = zzei.zzcb();
                            obj = zzca.zza(zzbz, this.zzot, i);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzca.zza(zzei, obj, zzbz, zzb);
                            } else {
                                zzbd = zzei.zzca();
                            }
                        } else if (!zzei.zzcr()) {
                            break;
                        }
                    }
                    if (zzei.getTag() != 12) {
                        throw zzct.zzea();
                    } else if (zzbd != null) {
                        if (obj != null) {
                            zzca.zza(zzbd, obj, zzbz, zzb);
                        } else {
                            zzfb.zza(zzq, i, zzbd);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzca.zza(zzbz, this.zzot, tag >>> 3);
                    if (zza != null) {
                        zzca.zza(zzei, zza, zzbz, zzb);
                    } else {
                        z = zzfb.zza(zzq, zzei);
                        continue;
                    }
                } else {
                    z = zzei.zzcr();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzfb.zzf(t, zzq);
            }
        } while (z);
    }

    public final void zzc(T t) {
        this.zzpd.zzc(t);
        this.zzpe.zzc(t);
    }

    public final boolean zzn(T t) {
        return this.zzpe.zza((Object) t).isInitialized();
    }

    public final int zzm(T t) {
        zzfb<?, ?> zzfb = this.zzpd;
        int zzr = zzfb.zzr(zzfb.zzp(t)) + 0;
        return this.zzou ? zzr + this.zzpe.zza((Object) t).zzdh() : zzr;
    }
}
