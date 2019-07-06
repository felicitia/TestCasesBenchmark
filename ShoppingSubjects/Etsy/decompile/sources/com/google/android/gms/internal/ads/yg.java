package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class yg implements yf {
    yg() {
    }

    public final int a(int i, Object obj, Object obj2) {
        zzbco zzbco = (zzbco) obj;
        if (zzbco.isEmpty()) {
            return 0;
        }
        Iterator it = zzbco.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    public final Object a(Object obj, Object obj2) {
        zzbco zzbco = (zzbco) obj;
        zzbco zzbco2 = (zzbco) obj2;
        if (!zzbco2.isEmpty()) {
            if (!zzbco.isMutable()) {
                zzbco = zzbco.zzaec();
            }
            zzbco.zza(zzbco2);
        }
        return zzbco;
    }

    public final Map<?, ?> a(Object obj) {
        return (zzbco) obj;
    }

    public final Map<?, ?> b(Object obj) {
        return (zzbco) obj;
    }

    public final boolean c(Object obj) {
        return !((zzbco) obj).isMutable();
    }

    public final Object d(Object obj) {
        ((zzbco) obj).zzaaz();
        return obj;
    }

    public final Object e(Object obj) {
        return zzbco.zzaeb().zzaec();
    }

    public final ye<?, ?> f(Object obj) {
        throw new NoSuchMethodError();
    }
}
