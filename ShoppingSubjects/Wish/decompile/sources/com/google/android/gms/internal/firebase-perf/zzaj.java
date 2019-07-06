package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zzb;
import com.google.android.gms.internal.firebase-perf.zzcm.zze;

public final class zzaj extends zzcm<zzaj, zza> implements zzdv {
    private static volatile zzed<zzaj> zzfm;
    /* access modifiers changed from: private */
    public static final zzaj zzfv = new zzaj();
    private int zzfh;
    private String zzfr = "";
    private String zzfs = "";
    private String zzft = "";
    private com.google.android.gms.internal.firebase-perf.zzgk.zza zzfu;

    public static final class zza extends com.google.android.gms.internal.firebase-perf.zzcm.zza<zzaj, zza> implements zzdv {
        private zza() {
            super(zzaj.zzfv);
        }

        /* synthetic */ zza(zzak zzak) {
            this();
        }
    }

    private zzaj() {
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzak.zzfn[i - 1]) {
            case 1:
                return new zzaj();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfh", "zzfr", "zzfs", "zzft", "zzfu"};
                return zza((zzdt) zzfv, "\u0001\u0004\u0000\u0001\u0002\u0005\u0004\u0000\u0000\u0000\u0002\b\u0000\u0003\b\u0001\u0004\b\u0002\u0005\t\u0003", objArr);
            case 4:
                return zzfv;
            case 5:
                zzed<zzaj> zzed = zzfm;
                if (zzed == null) {
                    synchronized (zzaj.class) {
                        zzed = zzfm;
                        if (zzed == null) {
                            zzed = new zzb<>(zzfv);
                            zzfm = zzed;
                        }
                    }
                }
                return zzed;
            case 6:
                return Byte.valueOf(1);
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static zzed<zzaj> zzau() {
        return (zzed) zzfv.zza(zze.zzmp, (Object) null, (Object) null);
    }

    static {
        zzcm.zza(zzaj.class, zzfv);
    }
}
