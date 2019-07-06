package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zzd;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzbz {
    private static volatile boolean zziy = false;
    private static final Class<?> zziz = zzcz();
    private static volatile zzbz zzja;
    static final zzbz zzjb = new zzbz(true);
    private final Map<zza, zzd<?, ?>> zzjc;

    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.object == zza.object && this.number == zza.number) {
                return true;
            }
            return false;
        }
    }

    private static Class<?> zzcz() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzbz zzda() {
        return zzby.zzcx();
    }

    public static zzbz zzdb() {
        zzbz zzbz = zzja;
        if (zzbz == null) {
            synchronized (zzbz.class) {
                zzbz = zzja;
                if (zzbz == null) {
                    zzbz = zzby.zzcy();
                    zzja = zzbz;
                }
            }
        }
        return zzbz;
    }

    static zzbz zzcy() {
        return zzck.zza(zzbz.class);
    }

    public final <ContainingType extends zzdt> zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzd) this.zzjc.get(new zza(containingtype, i));
    }

    zzbz() {
        this.zzjc = new HashMap();
    }

    private zzbz(boolean z) {
        this.zzjc = Collections.emptyMap();
    }
}
