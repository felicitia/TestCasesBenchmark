package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

final /* synthetic */ class ju implements jz {
    private final String a;
    private final String b;
    private final Map c;
    private final byte[] d;

    ju(String str, String str2, Map map, byte[] bArr) {
        this.a = str;
        this.b = str2;
        this.c = map;
        this.d = bArr;
    }

    public final void a(JsonWriter jsonWriter) {
        jt.a(this.a, this.b, this.c, this.d, jsonWriter);
    }
}
