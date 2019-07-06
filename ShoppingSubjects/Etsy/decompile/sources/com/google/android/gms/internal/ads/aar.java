package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class aar {
    protected volatile int Z = -1;

    public static final <T extends aar> T a(T t, byte[] bArr) throws zzbfh {
        return a(t, bArr, 0, bArr.length);
    }

    private static final <T extends aar> T a(T t, byte[] bArr, int i, int i2) throws zzbfh {
        try {
            aaj a = aaj.a(bArr, 0, i2);
            t.a(a);
            a.a(0);
            return t;
        } catch (zzbfh e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public static final byte[] a(aar aar) {
        byte[] bArr = new byte[aar.d()];
        try {
            aal a = aal.a(bArr, 0, bArr.length);
            aar.a(a);
            a.a();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    /* access modifiers changed from: protected */
    public int a() {
        return 0;
    }

    public abstract aar a(aaj aaj) throws IOException;

    public void a(aal aal) throws IOException {
    }

    /* renamed from: c */
    public aar clone() throws CloneNotSupportedException {
        return (aar) super.clone();
    }

    public final int d() {
        int a = a();
        this.Z = a;
        return a;
    }

    public String toString() {
        return aas.a(this);
    }
}
