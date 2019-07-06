package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

final /* synthetic */ class jw implements jz {
    private final int a;
    private final Map b;

    jw(int i, Map map) {
        this.a = i;
        this.b = map;
    }

    public final void a(JsonWriter jsonWriter) {
        jt.a(this.a, this.b, jsonWriter);
    }
}
