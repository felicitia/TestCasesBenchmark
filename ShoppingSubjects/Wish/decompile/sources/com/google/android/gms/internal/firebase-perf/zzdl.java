package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzdl<K, V> {
    static <K, V> void zza(zzbt zzbt, zzdm<K, V> zzdm, K k, V v) throws IOException {
        zzcd.zza(zzbt, zzdm.zzog, 1, k);
        zzcd.zza(zzbt, zzdm.zzoi, 2, v);
    }

    static <K, V> int zza(zzdm<K, V> zzdm, K k, V v) {
        return zzcd.zza(zzdm.zzog, 1, k) + zzcd.zza(zzdm.zzoi, 2, v);
    }
}
