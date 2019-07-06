package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;

@bu
public final class mw extends gq {
    final mo a;
    final mz b;
    private final String c;

    mw(mo moVar, mz mzVar, String str) {
        this.a = moVar;
        this.b = mzVar;
        this.c = str;
        ao.z().a(this);
    }

    public final void a() {
        try {
            this.b.a(this.c);
        } finally {
            hd.a.post(new mx(this));
        }
    }

    public final void c_() {
        this.b.a();
    }
}
