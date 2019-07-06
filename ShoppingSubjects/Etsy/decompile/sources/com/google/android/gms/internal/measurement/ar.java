package com.google.android.gms.internal.measurement;

import com.etsy.android.lib.convos.Draft;

final class ar implements Runnable {
    private final /* synthetic */ int a;
    private final /* synthetic */ String b;
    private final /* synthetic */ Object c;
    private final /* synthetic */ Object d;
    private final /* synthetic */ Object e;
    private final /* synthetic */ aq f;

    ar(aq aqVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.f = aqVar;
        this.a = i;
        this.b = str;
        this.c = obj;
        this.d = obj2;
        this.e = obj3;
    }

    public final void run() {
        aq aqVar;
        char c2;
        bb c3 = this.f.q.c();
        if (!c3.y()) {
            this.f.a(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.f.a == 0) {
            if (this.f.t().g()) {
                aqVar = this.f;
                this.f.u();
                c2 = 'C';
            } else {
                aqVar = this.f;
                this.f.u();
                c2 = 'c';
            }
            aqVar.a = c2;
        }
        if (this.f.b < 0) {
            this.f.b = this.f.t().f();
        }
        char charAt = "01VDIWEA?".charAt(this.a);
        char a2 = this.f.a;
        long b2 = this.f.b;
        String a3 = aq.a(true, this.b, this.c, this.d, this.e);
        StringBuilder sb = new StringBuilder(24 + String.valueOf(a3).length());
        sb.append("2");
        sb.append(charAt);
        sb.append(a2);
        sb.append(b2);
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(a3);
        String sb2 = sb.toString();
        if (sb2.length() > 1024) {
            sb2 = this.b.substring(0, 1024);
        }
        c3.b.a(sb2, 1);
    }
}
