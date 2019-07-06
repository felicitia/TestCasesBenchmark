package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ai;
import com.google.android.gms.ads.internal.bg;
import java.util.concurrent.Callable;

final /* synthetic */ class pa implements Callable {
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

    pa(Context context, ot otVar, String str, boolean z, boolean z2, ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) {
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
        pb a2 = pb.a(context, otVar, str, z, z2, this.f, this.g, this.h, this.i, this.j, this.k);
        zzarh zzarh = new zzarh(a2);
        ou ouVar = new ou(zzarh, z2);
        a2.setWebChromeClient(new zzaqo(zzarh));
        a2.zza((pg) ouVar);
        a2.zza((pl) ouVar);
        a2.zza((pk) ouVar);
        a2.zza((pi) ouVar);
        a2.a(ouVar);
        return zzarh;
    }
}
