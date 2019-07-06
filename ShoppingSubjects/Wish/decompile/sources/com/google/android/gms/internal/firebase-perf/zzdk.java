package com.google.android.gms.internal.firebase-perf;

final class zzdk implements zzds {
    private zzds[] zzof;

    zzdk(zzds... zzdsArr) {
        this.zzof = zzdsArr;
    }

    public final boolean zzb(Class<?> cls) {
        for (zzds zzb : this.zzof) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzdr zzc(Class<?> cls) {
        zzds[] zzdsArr;
        for (zzds zzds : this.zzof) {
            if (zzds.zzb(cls)) {
                return zzds.zzc(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
