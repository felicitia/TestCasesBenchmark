package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zze;

final class zzdi implements zzek {
    private static final zzds zzoe = new zzdj();
    private final zzds zzod;

    public zzdi() {
        this(new zzdk(zzcl.zzdo(), zzem()));
    }

    private zzdi(zzds zzds) {
        this.zzod = (zzds) zzco.zza(zzds, "messageInfoFactory");
    }

    public final <T> zzej<T> zze(Class<T> cls) {
        zzel.zzg(cls);
        zzdr zzc = this.zzod.zzc(cls);
        if (zzc.zzeu()) {
            if (zzcm.class.isAssignableFrom(cls)) {
                return zzdy.zza(zzel.zzfg(), zzcc.zzdd(), zzc.zzev());
            }
            return zzdy.zza(zzel.zzfe(), zzcc.zzde(), zzc.zzev());
        } else if (zzcm.class.isAssignableFrom(cls)) {
            if (zza(zzc)) {
                return zzdx.zza(cls, zzc, zzec.zzey(), zzdd.zzel(), zzel.zzfg(), zzcc.zzdd(), zzdq.zzer());
            }
            return zzdx.zza(cls, zzc, zzec.zzey(), zzdd.zzel(), zzel.zzfg(), null, zzdq.zzer());
        } else if (zza(zzc)) {
            return zzdx.zza(cls, zzc, zzec.zzex(), zzdd.zzek(), zzel.zzfe(), zzcc.zzde(), zzdq.zzeq());
        } else {
            return zzdx.zza(cls, zzc, zzec.zzex(), zzdd.zzek(), zzel.zzff(), null, zzdq.zzeq());
        }
    }

    private static boolean zza(zzdr zzdr) {
        return zzdr.zzet() == zze.zzmr;
    }

    private static zzds zzem() {
        try {
            return (zzds) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzoe;
        }
    }
}
