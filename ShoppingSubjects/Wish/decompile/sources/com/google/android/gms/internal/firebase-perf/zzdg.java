package com.google.android.gms.internal.firebase-perf;

import java.util.List;

final class zzdg extends zzdd {
    private zzdg() {
        super();
    }

    /* access modifiers changed from: 0000 */
    public final <L> List<L> zza(Object obj, long j) {
        zzcs zzd = zzd(obj, j);
        if (zzd.zzbh()) {
            return zzd;
        }
        int size = zzd.size();
        zzcs zzi = zzd.zzi(size == 0 ? 10 : size << 1);
        zzfh.zza(obj, j, (Object) zzi);
        return zzi;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzbi();
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzcs zzd = zzd(obj, j);
        zzcs zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzbh()) {
                zzd = zzd.zzi(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size > 0) {
            zzd2 = zzd;
        }
        zzfh.zza(obj, j, (Object) zzd2);
    }

    private static <E> zzcs<E> zzd(Object obj, long j) {
        return (zzcs) zzfh.zzp(obj, j);
    }
}
