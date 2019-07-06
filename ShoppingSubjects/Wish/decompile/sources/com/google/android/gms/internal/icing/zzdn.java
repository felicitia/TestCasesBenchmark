package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzdn implements zzdm {
    zzdn() {
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzdl zzdl = (zzdl) obj;
        if (zzdl.isEmpty()) {
            return 0;
        }
        Iterator it = zzdl.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    public final Object zzb(Object obj, Object obj2) {
        zzdl zzdl = (zzdl) obj;
        zzdl zzdl2 = (zzdl) obj2;
        if (!zzdl2.isEmpty()) {
            if (!zzdl.isMutable()) {
                zzdl = zzdl.zzbn();
            }
            zzdl.zza(zzdl2);
        }
        return zzdl;
    }

    public final Map<?, ?> zzg(Object obj) {
        return (zzdl) obj;
    }

    public final Object zzh(Object obj) {
        ((zzdl) obj).zzp();
        return obj;
    }

    public final zzdk<?, ?> zzi(Object obj) {
        throw new NoSuchMethodError();
    }
}
