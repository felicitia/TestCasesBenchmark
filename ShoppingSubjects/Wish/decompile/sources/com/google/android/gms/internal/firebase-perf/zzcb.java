package com.google.android.gms.internal.firebase-perf;

import com.google.android.gms.internal.firebase-perf.zzcm.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzcb extends zzca<Object> {
    zzcb() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zze(zzdt zzdt) {
        return zzdt instanceof zzc;
    }

    /* access modifiers changed from: 0000 */
    public final zzcd<Object> zza(Object obj) {
        return ((zzc) obj).zzmi;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(Object obj, zzcd<Object> zzcd) {
        ((zzc) obj).zzmi = zzcd;
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
        zza(obj).zzbi();
    }

    /* access modifiers changed from: 0000 */
    public final <UT, UB> UB zza(zzei zzei, Object obj, zzbz zzbz, zzcd<Object> zzcd, UB ub, zzfb<UT, UB> zzfb) throws IOException {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final int zza(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfw zzfw, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final Object zza(zzbz zzbz, zzdt zzdt, int i) {
        return zzbz.zza(zzdt, i);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzei zzei, Object obj, zzbz zzbz, zzcd<Object> zzcd) throws IOException {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbd zzbd, Object obj, zzbz zzbz, zzcd<Object> zzcd) throws IOException {
        throw new NoSuchMethodError();
    }
}
