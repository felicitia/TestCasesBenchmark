package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzck.zzd;

final class zzcj implements zzdq {
    private static final zzcj zzhl = new zzcj();

    private zzcj() {
    }

    public static zzcj zzau() {
        return zzhl;
    }

    public final boolean zza(Class<?> cls) {
        return zzck.class.isAssignableFrom(cls);
    }

    public final zzdp zzb(Class<?> cls) {
        if (!zzck.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (zzdp) zzck.zzc(cls.asSubclass(zzck.class)).zza(zzd.zzhv, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
