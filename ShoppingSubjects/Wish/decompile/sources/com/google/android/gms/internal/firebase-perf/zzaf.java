package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zzb;

public final class zzaf extends zzcm<zzaf, zza> implements zzdv {
    /* access modifiers changed from: private */
    public static final zzaf zzfl = new zzaf();
    private static volatile zzed<zzaf> zzfm;
    private int zzfh;
    private long zzfi;
    private long zzfj;
    private long zzfk;

    public static final class zza extends com.google.android.gms.internal.firebase-perf.zzcm.zza<zzaf, zza> implements zzdv {
        private zza() {
            super(zzaf.zzfl);
        }

        /* synthetic */ zza(zzag zzag) {
            this();
        }
    }

    private zzaf() {
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzag.zzfn[i - 1]) {
            case 1:
                return new zzaf();
            case 2:
                return new zza(null);
            case 3:
                Object[] objArr = {"zzfh", "zzfi", "zzfj", "zzfk"};
                return zza((zzdt) zzfl, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001\u0003\u0002\u0002", objArr);
            case 4:
                return zzfl;
            case 5:
                zzed<zzaf> zzed = zzfm;
                if (zzed == null) {
                    synchronized (zzaf.class) {
                        zzed = zzfm;
                        if (zzed == null) {
                            zzed = new zzb<>(zzfl);
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

    static {
        zzcm.zza(zzaf.class, zzfl);
    }
}
