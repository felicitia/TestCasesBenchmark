package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zze;

final class zzcl implements zzds {
    private static final zzcl zzmb = new zzcl();

    private zzcl() {
    }

    public static zzcl zzdo() {
        return zzmb;
    }

    public final boolean zzb(Class<?> cls) {
        return zzcm.class.isAssignableFrom(cls);
    }

    public final zzdr zzc(Class<?> cls) {
        if (!zzcm.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzdr) zzcm.zzd(cls.asSubclass(zzcm.class)).zza(zze.zzml, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
