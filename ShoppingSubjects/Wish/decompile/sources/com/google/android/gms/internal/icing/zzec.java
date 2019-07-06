package com.google.android.gms.internal.icing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzec {
    private static final zzec zzkr = new zzec();
    private final zzeg zzks;
    private final ConcurrentMap<Class<?>, zzef<?>> zzkt = new ConcurrentHashMap();

    private zzec() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzeg zzeg = null;
        for (int i = 0; i <= 0; i++) {
            zzeg = zzk(strArr[0]);
            if (zzeg != null) {
                break;
            }
        }
        if (zzeg == null) {
            zzeg = new zzdh();
        }
        this.zzks = zzeg;
    }

    public static zzec zzbz() {
        return zzkr;
    }

    private static zzeg zzk(String str) {
        try {
            return (zzeg) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final <T> zzef<T> zze(Class<T> cls) {
        zzcm.zza(cls, "messageType");
        zzef<T> zzef = (zzef) this.zzkt.get(cls);
        if (zzef != null) {
            return zzef;
        }
        zzef<T> zzd = this.zzks.zzd(cls);
        zzcm.zza(cls, "messageType");
        zzcm.zza(zzd, "schema");
        zzef zzef2 = (zzef) this.zzkt.putIfAbsent(cls, zzd);
        return zzef2 != null ? zzef2 : zzd;
    }

    public final <T> zzef<T> zzl(T t) {
        return zze(t.getClass());
    }
}
