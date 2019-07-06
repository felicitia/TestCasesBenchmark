package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;

@bu
public final class jd extends gq {
    private final kb a;
    private final String b;

    public jd(Context context, String str, String str2) {
        this(str2, ao.e().b(context, str));
    }

    private jd(String str, String str2) {
        this.a = new kb(str2);
        this.b = str;
    }

    public final void a() {
        this.a.a(this.b);
    }

    public final void c_() {
    }
}
