package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class as implements Runnable {
    private final ar a;
    private final JSONObject b;
    private final le c;

    as(ar arVar, JSONObject jSONObject, le leVar) {
        this.a = arVar;
        this.b = jSONObject;
        this.c = leVar;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
