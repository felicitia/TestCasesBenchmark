package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

final class zzfd extends zzfb<zzfc, zzfc> {
    zzfd() {
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzei zzei) {
        return false;
    }

    private static void zza(Object obj, zzfc zzfc) {
        ((zzcm) obj).zzmc = zzfc;
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(Object obj) {
        ((zzcm) obj).zzmc.zzbi();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int zzm(Object obj) {
        return ((zzfc) obj).zzdg();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ int zzr(Object obj) {
        return ((zzfc) obj).zzfu();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzg(Object obj, Object obj2) {
        zzfc zzfc = (zzfc) obj;
        zzfc zzfc2 = (zzfc) obj2;
        if (zzfc2.equals(zzfc.zzfs())) {
            return zzfc;
        }
        return zzfc.zza(zzfc, zzfc2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(Object obj, zzfw zzfw) throws IOException {
        ((zzfc) obj).zza(zzfw);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, zzfw zzfw) throws IOException {
        ((zzfc) obj).zzb(zzfw);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzfc) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzq(Object obj) {
        zzfc zzfc = ((zzcm) obj).zzmc;
        if (zzfc != zzfc.zzfs()) {
            return zzfc;
        }
        zzfc zzft = zzfc.zzft();
        zza(obj, zzft);
        return zzft;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzp(Object obj) {
        return ((zzcm) obj).zzmc;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zze(Object obj, Object obj2) {
        zza(obj, (zzfc) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzj(Object obj) {
        zzfc zzfc = (zzfc) obj;
        zzfc.zzbi();
        return zzfc;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object zzfr() {
        return zzfc.zzft();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzfc) obj).zzb((i << 3) | 3, (zzfc) obj2);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, zzbd zzbd) {
        ((zzfc) obj).zzb((i << 3) | 2, zzbd);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzfc) obj).zzb((i << 3) | 1, Long.valueOf(j));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzfc) obj).zzb((i << 3) | 5, Integer.valueOf(i2));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzfc) obj).zzb(i << 3, Long.valueOf(j));
    }
}
