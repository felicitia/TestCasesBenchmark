package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

final class fa implements aw {
    private final /* synthetic */ String a;
    private final /* synthetic */ ey b;

    fa(ey eyVar, String str) {
        this.b = eyVar;
        this.a = str;
    }

    public final void a(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.b.a(i, th, bArr, this.a);
    }
}
