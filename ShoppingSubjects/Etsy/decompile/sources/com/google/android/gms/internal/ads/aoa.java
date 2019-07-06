package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.zzal;

final class aoa {
    zzal a;
    @Nullable
    zzjj b;
    amt c;
    long d;
    boolean e;
    boolean f;
    private final /* synthetic */ anz g;

    aoa(anz anz, ams ams) {
        this.g = anz;
        this.a = ams.b(anz.c);
        this.c = new amt();
        amt amt = this.c;
        zzal zzal = this.a;
        zzal.zza((zzkh) new amu(amt));
        zzal.zza((zzla) new and(amt));
        zzal.zza((zzod) new anf(amt));
        zzal.zza((zzke) new anh(amt));
        zzal.zza((zzahe) new anj(amt));
    }

    aoa(anz anz, ams ams, zzjj zzjj) {
        this(anz, ams);
        this.b = zzjj;
    }

    /* access modifiers changed from: 0000 */
    public final boolean a() {
        if (this.e) {
            return false;
        }
        this.f = this.a.zzb(anx.b(this.b != null ? this.b : this.g.b));
        this.e = true;
        this.d = ao.l().currentTimeMillis();
        return true;
    }
}
