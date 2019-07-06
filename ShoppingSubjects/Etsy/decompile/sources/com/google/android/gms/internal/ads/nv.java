package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ai;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;
import java.util.concurrent.Callable;

final /* synthetic */ class nv implements Callable {
    private final Context a;
    private final ot b;
    private final String c;
    private final boolean d;
    private final boolean e;
    private final ack f;
    private final zzang g;
    private final aky h;
    private final ai i;
    private final bg j;
    private final ahh k;

    nv(Context context, ot otVar, String str, boolean z, boolean z2, ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) {
        this.a = context;
        this.b = otVar;
        this.c = str;
        this.d = z;
        this.e = z2;
        this.f = ack;
        this.g = zzang;
        this.h = aky;
        this.i = aiVar;
        this.j = bgVar;
        this.k = ahh;
    }

    public final Object call() {
        Context context = this.a;
        ot otVar = this.b;
        String str = this.c;
        boolean z = this.d;
        boolean z2 = this.e;
        zzarh zzarh = new zzarh(nx.a(context, otVar, str, z, z2, this.f, this.g, this.h, this.i, this.j, this.k));
        zzarh.setWebViewClient(ao.g().a((nn) zzarh, z2));
        zzarh.setWebChromeClient(new zzaqo(zzarh));
        return zzarh;
    }
}
