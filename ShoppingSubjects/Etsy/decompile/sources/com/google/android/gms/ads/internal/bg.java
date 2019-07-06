package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.internal.ads.ahl;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.fi;
import com.google.android.gms.internal.ads.fj;
import com.google.android.gms.internal.ads.fm;
import com.google.android.gms.internal.ads.mb;
import com.google.android.gms.internal.ads.mh;
import com.google.android.gms.internal.ads.mt;
import com.google.android.gms.internal.ads.ne;

@bu
public final class bg {
    public final ne a;
    public final mb b;
    public final fm c;
    public final ahl d;

    private bg(ne neVar, mb mbVar, fm fmVar, ahl ahl) {
        this.a = neVar;
        this.b = mbVar;
        this.c = fmVar;
        this.d = ahl;
    }

    public static bg a(Context context) {
        return new bg(new mt(), new mh(), new fi(new fj()), new ahl(context));
    }
}
