package com.google.android.gms.internal.firebase-perf;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzdp implements zzdo {
    zzdp() {
    }

    public final Map<?, ?> zzg(Object obj) {
        return (zzdn) obj;
    }

    public final zzdm<?, ?> zzl(Object obj) {
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzh(Object obj) {
        return (zzdn) obj;
    }

    public final boolean zzi(Object obj) {
        return !((zzdn) obj).isMutable();
    }

    public final Object zzj(Object obj) {
        ((zzdn) obj).zzbi();
        return obj;
    }

    public final Object zzk(Object obj) {
        return zzdn.zzen().zzeo();
    }

    public final Object zzb(Object obj, Object obj2) {
        zzdn zzdn = (zzdn) obj;
        zzdn zzdn2 = (zzdn) obj2;
        if (!zzdn2.isEmpty()) {
            if (!zzdn.isMutable()) {
                zzdn = zzdn.zzeo();
            }
            zzdn.zza(zzdn2);
        }
        return zzdn;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzdn zzdn = (zzdn) obj;
        if (zzdn.isEmpty()) {
            return 0;
        }
        Iterator it = zzdn.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
