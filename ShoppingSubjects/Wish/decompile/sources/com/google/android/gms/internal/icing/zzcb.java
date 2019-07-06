package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzck.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzcb extends zzca<Object> {
    zzcb() {
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final zzcd<Object> zza(Object obj) {
        return ((zzc) obj).zzhs;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfr zzfr, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Object obj, zzcd<Object> zzcd) {
        ((zzc) obj).zzhs = zzcd;
    }

    /* access modifiers changed from: 0000 */
    public final zzcd<Object> zzb(Object obj) {
        zzcd<Object> zza = zza(obj);
        if (!zza.isImmutable()) {
            return zza;
        }
        zzcd<Object> zzcd = (zzcd) zza.clone();
        zza(obj, zzcd);
        return zzcd;
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(Object obj) {
        zza(obj).zzp();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zze(zzdr zzdr) {
        return zzdr instanceof zzc;
    }
}
