package com.google.android.gms.internal.ads;

import java.util.Map;

final class is extends ra {
    private final /* synthetic */ byte[] a;
    private final /* synthetic */ Map b;
    private final /* synthetic */ jt c;

    is(in inVar, int i, String str, art art, arn arn, byte[] bArr, Map map, jt jtVar) {
        this.a = bArr;
        this.b = map;
        this.c = jtVar;
        super(i, str, art, arn);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void a(Object obj) {
        a((String) obj);
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        this.c.a(str);
        super.a(str);
    }

    public final byte[] a() throws zza {
        return this.a == null ? super.a() : this.a;
    }

    public final Map<String, String> b() throws zza {
        return this.b == null ? super.b() : this.b;
    }
}
