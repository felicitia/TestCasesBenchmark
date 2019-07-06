package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzck.zzd;

final class zzdh implements zzeg {
    private static final zzdq zzjp = new zzdi();
    private final zzdq zzjo;

    public zzdh() {
        this(new zzdj(zzcj.zzau(), zzbm()));
    }

    private zzdh(zzdq zzdq) {
        this.zzjo = (zzdq) zzcm.zza(zzdq, "messageInfoFactory");
    }

    private static boolean zza(zzdp zzdp) {
        return zzdp.zzbs() == zzd.zzib;
    }

    private static zzdq zzbm() {
        try {
            return (zzdq) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzjp;
        }
    }

    public final <T> zzef<T> zzd(Class<T> cls) {
        zzeh.zzf(cls);
        zzdp zzb = this.zzjo.zzb(cls);
        if (zzb.zzbt()) {
            return zzck.class.isAssignableFrom(cls) ? zzdw.zza(zzeh.zzcf(), zzcc.zzak(), zzb.zzbu()) : zzdw.zza(zzeh.zzcd(), zzcc.zzal(), zzb.zzbu());
        }
        if (zzck.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzdv.zza(cls, zzb, zzea.zzbx(), zzdc.zzbk(), zzeh.zzcf(), zzcc.zzak(), zzdo.zzbq());
            }
            return zzdv.zza(cls, zzb, zzea.zzbx(), zzdc.zzbk(), zzeh.zzcf(), null, zzdo.zzbq());
        } else if (zza(zzb)) {
            return zzdv.zza(cls, zzb, zzea.zzbw(), zzdc.zzbj(), zzeh.zzcd(), zzcc.zzal(), zzdo.zzbp());
        } else {
            return zzdv.zza(cls, zzb, zzea.zzbw(), zzdc.zzbj(), zzeh.zzce(), null, zzdo.zzbp());
        }
    }
}
