package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;

@bu
public final class de {
    public final dp a = null;
    public final ahe b;
    public final fv c;
    public final ajz d;
    public final dz e;
    public final aqv f;
    public final ea g;
    public final eb h;
    public final r i;
    public final fz j;
    public final boolean k;
    public final di l;

    private de(dp dpVar, ahe ahe, fv fvVar, ajz ajz, dz dzVar, aqv aqv, ea eaVar, eb ebVar, r rVar, fz fzVar, boolean z, di diVar) {
        this.b = ahe;
        this.c = fvVar;
        this.d = ajz;
        this.e = dzVar;
        this.f = aqv;
        this.g = eaVar;
        this.h = ebVar;
        this.i = rVar;
        this.j = fzVar;
        this.k = true;
        this.l = diVar;
    }

    public static de a(Context context) {
        ao.C().a(context);
        ef efVar = new ef(context);
        de deVar = new de(null, new ahf(), new fw(), new ajy(), new dx(context, efVar.b()), new aqw(), new ed(), new ee(), new q(), new fx(), true, efVar);
        return deVar;
    }
}
