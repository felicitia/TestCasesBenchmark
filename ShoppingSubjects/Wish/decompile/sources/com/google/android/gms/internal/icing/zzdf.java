package com.google.android.gms.internal.icing;

final class zzdf extends zzdc {
    private zzdf() {
        super();
    }

    private static <E> zzcr<E> zzc(Object obj, long j) {
        return (zzcr) zzfd.zzo(obj, j);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Object obj, long j) {
        zzc(obj, j).zzp();
    }

    /* access modifiers changed from: 0000 */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzcr zzc = zzc(obj, j);
        zzcr zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzo()) {
                zzc = zzc.zzh(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzfd.zza(obj, j, (Object) zzc2);
    }
}
