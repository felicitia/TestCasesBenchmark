package com.google.android.gms.internal.icing;

final class zzdj implements zzdq {
    private zzdq[] zzjq;

    zzdj(zzdq... zzdqArr) {
        this.zzjq = zzdqArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzdq zza : this.zzjq) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzdp zzb(Class<?> cls) {
        zzdq[] zzdqArr;
        for (zzdq zzdq : this.zzjq) {
            if (zzdq.zza(cls)) {
                return zzdq.zzb(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
