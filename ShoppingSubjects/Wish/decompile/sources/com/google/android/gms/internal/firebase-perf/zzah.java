package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zzb;
import com.google.android.gms.internal.firebase-perf.zzcm.zze;

public final class zzah extends zzcm<zzah, zza> implements zzdv {
    private static volatile zzed<zzah> zzfm;
    /* access modifiers changed from: private */
    public static final zzah zzfq = new zzah();
    private int zzfh;
    private String zzfo = "";
    private zzcs<zzaf> zzfp = zzdp();

    public static final class zza extends com.google.android.gms.internal.firebase-perf.zzcm.zza<zzah, zza> implements zzdv {
        private zza() {
            super(zzah.zzfq);
        }

        /* synthetic */ zza(zzai zzai) {
            this();
        }
    }

    private zzah() {
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzai.zzfn[i - 1]) {
            case 1:
                return new zzah();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfh", "zzfo", "zzfp", zzaf.class};
                return zza((zzdt) zzfq, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b", objArr);
            case 4:
                return zzfq;
            case 5:
                zzed<zzah> zzed = zzfm;
                if (zzed == null) {
                    synchronized (zzah.class) {
                        zzed = zzfm;
                        if (zzed == null) {
                            zzed = new zzb<>(zzfq);
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

    public static zzed<zzah> zzau() {
        return (zzed) zzfq.zza(zze.zzmp, (Object) null, (Object) null);
    }

    static {
        zzcm.zza(zzah.class, zzfq);
    }
}
