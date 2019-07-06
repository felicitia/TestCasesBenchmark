package com.google.android.gms.internal.ads;

final class cx implements lj<aqd> {
    private final /* synthetic */ cw a;

    cx(cw cwVar) {
        this.a = cwVar;
    }

    public final /* synthetic */ void a(Object obj) {
        try {
            ((aqd) obj).zzb("AFMA_getAdapterLessMediationAd", this.a.a);
        } catch (Exception e) {
            gv.b("Error requesting an ad url", e);
            cu.f.b(this.a.b);
        }
    }
}
