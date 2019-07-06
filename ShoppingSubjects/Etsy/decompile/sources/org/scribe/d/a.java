package org.scribe.d;

/* compiled from: Base64Encoder */
public abstract class a {
    private static a a;

    public abstract String a(byte[] bArr);

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = b();
            }
            aVar = a;
        }
        return aVar;
    }

    private static a b() {
        if (b.b()) {
            return new b();
        }
        return new c();
    }
}
