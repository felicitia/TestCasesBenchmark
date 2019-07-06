package com.google.android.gms.internal.firebase-perf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzef {
    private static final zzef zzpi = new zzef();
    private final zzek zzpj;
    private final ConcurrentMap<Class<?>, zzej<?>> zzpk = new ConcurrentHashMap();

    public static zzef zzfa() {
        return zzpi;
    }

    public final <T> zzej<T> zzf(Class<T> cls) {
        zzco.zza(cls, "messageType");
        zzej<T> zzej = (zzej) this.zzpk.get(cls);
        if (zzej != null) {
            return zzej;
        }
        zzej<T> zze = this.zzpj.zze(cls);
        zzco.zza(cls, "messageType");
        zzco.zza(zze, "schema");
        zzej zzej2 = (zzej) this.zzpk.putIfAbsent(cls, zze);
        return zzej2 != null ? zzej2 : zze;
    }

    public final <T> zzej<T> zzo(T t) {
        return zzf(t.getClass());
    }

    private zzef() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzek zzek = null;
        for (int i = 0; i <= 0; i++) {
            zzek = zzr(strArr[0]);
            if (zzek != null) {
                break;
            }
        }
        if (zzek == null) {
            zzek = new zzdi();
        }
        this.zzpj = zzek;
    }

    private static zzek zzr(String str) {
        try {
            return (zzek) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }
}
