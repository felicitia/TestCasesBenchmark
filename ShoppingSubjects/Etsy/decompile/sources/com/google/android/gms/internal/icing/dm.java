package com.google.android.gms.internal.icing;

import java.io.IOException;

public class dm {
    protected volatile int e = -1;

    public static final byte[] a(dm dmVar) {
        byte[] bArr = new byte[dmVar.d()];
        try {
            dg a = dg.a(bArr, 0, bArr.length);
            dmVar.a(a);
            a.a();
            return bArr;
        } catch (IOException e2) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e2);
        }
    }

    /* access modifiers changed from: protected */
    public int a() {
        return 0;
    }

    public void a(dg dgVar) throws IOException {
    }

    /* renamed from: c */
    public dm clone() throws CloneNotSupportedException {
        return (dm) super.clone();
    }

    public final int d() {
        int a = a();
        this.e = a;
        return a;
    }

    public String toString() {
        return dn.a(this);
    }
}
