package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;

final /* synthetic */ class nu implements kd {
    private final Context a;
    private final ack b;
    private final zzang c;
    private final bg d;
    private final String e;

    nu(Context context, ack ack, zzang zzang, bg bgVar, String str) {
        this.a = context;
        this.b = ack;
        this.c = zzang;
        this.d = bgVar;
        this.e = str;
    }

    public final kt a(Object obj) {
        Context context = this.a;
        ack ack = this.b;
        zzang zzang = this.c;
        bg bgVar = this.d;
        String str = this.e;
        ao.f();
        nn a2 = nt.a(context, ot.a(), "", false, false, ack, zzang, null, null, bgVar, ahh.a());
        ld a3 = ld.a(a2);
        a2.zzuf().zza((op) new nw(a3));
        a2.loadUrl(str);
        return a3;
    }
}
