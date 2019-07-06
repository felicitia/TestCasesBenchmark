package com.google.android.gms.internal.firebase-perf;

import java.util.Comparator;

final class zzbf implements Comparator<zzbd> {
    zzbf() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzbd zzbd = (zzbd) obj;
        zzbd zzbd2 = (zzbd) obj2;
        zzbj zzbj = (zzbj) zzbd.iterator();
        zzbj zzbj2 = (zzbj) zzbd2.iterator();
        while (zzbj.hasNext() && zzbj2.hasNext()) {
            int compare = Integer.compare(zzbd.zza(zzbj.nextByte()), zzbd.zza(zzbj2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzbd.size(), zzbd2.size());
    }
}
