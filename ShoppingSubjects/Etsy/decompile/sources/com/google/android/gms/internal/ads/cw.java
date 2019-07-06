package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class cw implements Runnable {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ String b;
    private final /* synthetic */ cu c;

    cw(cu cuVar, JSONObject jSONObject, String str) {
        this.c = cuVar;
        this.a = jSONObject;
        this.b = str;
    }

    public final void run() {
        this.c.l = cu.d.b((ack) null);
        this.c.l.a(new cx(this), new cy(this));
    }
}
