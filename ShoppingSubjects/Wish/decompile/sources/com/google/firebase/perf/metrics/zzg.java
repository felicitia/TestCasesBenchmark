package com.google.firebase.perf.metrics;

import com.google.android.gms.internal.firebase-perf.zzas;
import com.google.android.gms.internal.firebase-perf.zzat;
import com.google.android.gms.internal.firebase-perf.zzau;
import com.google.android.gms.internal.firebase-perf.zzav;
import com.google.android.gms.internal.firebase-perf.zzr;
import java.util.List;
import java.util.Map;

final class zzg {
    private final Trace zzdz;

    zzg(Trace trace) {
        this.zzdz = trace;
    }

    /* access modifiers changed from: 0000 */
    public final zzat zzao() {
        zzat zzat = new zzat();
        zzat.name = this.zzdz.getName();
        zzat.zzgk = Long.valueOf(this.zzdz.zzak().zzar());
        zzat.zzgz = Long.valueOf(this.zzdz.zzak().zza(this.zzdz.zzal()));
        Map zzaj = this.zzdz.zzaj();
        if (!zzaj.isEmpty()) {
            zzat.zzha = new zzau[zzaj.size()];
            int i = 0;
            for (String str : zzaj.keySet()) {
                zza zza = (zza) zzaj.get(str);
                zzau zzau = new zzau();
                zzau.key = str;
                zzau.zzhe = Long.valueOf(zza.getCount());
                int i2 = i + 1;
                zzat.zzha[i] = zzau;
                i = i2;
            }
        }
        List<Trace> zzam = this.zzdz.zzam();
        if (!zzam.isEmpty()) {
            zzat.zzhb = new zzat[zzam.size()];
            int i3 = 0;
            for (Trace zzg : zzam) {
                int i4 = i3 + 1;
                zzat.zzhb[i3] = new zzg(zzg).zzao();
                i3 = i4;
            }
        }
        Map attributes = this.zzdz.getAttributes();
        if (!attributes.isEmpty()) {
            zzat.zzhc = new zzav[attributes.size()];
            int i5 = 0;
            for (String str2 : attributes.keySet()) {
                String str3 = (String) attributes.get(str2);
                zzav zzav = new zzav();
                zzav.key = str2;
                zzav.value = str3;
                int i6 = i5 + 1;
                zzat.zzhc[i5] = zzav;
                i5 = i6;
            }
        }
        zzat.zzgp = new zzas[this.zzdz.zzan().size()];
        for (int i7 = 0; i7 < this.zzdz.zzan().size(); i7++) {
            zzat.zzgp[i7] = ((zzr) this.zzdz.zzan().get(i7)).zzt();
        }
        return zzat;
    }
}
