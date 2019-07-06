package com.onfido.d;

import java.util.EnumMap;
import java.util.Map;

public final class k {
    private final String a;
    private final byte[] b;
    private final int c;
    private m[] d;
    private final a e;
    private Map<l, Object> f;
    private final long g;

    public k(String str, byte[] bArr, int i, m[] mVarArr, a aVar, long j) {
        this.a = str;
        this.b = bArr;
        this.c = i;
        this.d = mVarArr;
        this.e = aVar;
        this.f = null;
        this.g = j;
    }

    public k(String str, byte[] bArr, m[] mVarArr, a aVar) {
        this(str, bArr, mVarArr, aVar, System.currentTimeMillis());
    }

    public k(String str, byte[] bArr, m[] mVarArr, a aVar, long j) {
        this(str, bArr, bArr == null ? 0 : bArr.length * 8, mVarArr, aVar, j);
    }

    public void a(l lVar, Object obj) {
        if (this.f == null) {
            this.f = new EnumMap(l.class);
        }
        this.f.put(lVar, obj);
    }

    public String toString() {
        return this.a;
    }
}
