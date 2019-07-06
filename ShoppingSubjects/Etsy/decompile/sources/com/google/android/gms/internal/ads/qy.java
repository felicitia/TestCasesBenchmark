package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class qy implements py {
    private static final byte[] a = new byte[0];
    private final tl b;
    private final py c;

    public qy(tl tlVar, py pyVar) {
        this.b = tlVar;
        this.c = pyVar;
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] i = qo.b(this.b).i();
        byte[] a2 = this.c.a(i, a);
        byte[] a3 = ((py) qo.a(this.b.a(), i)).a(bArr, bArr2);
        return ByteBuffer.allocate(4 + a2.length + a3.length).putInt(a2.length).put(a2).put(a3).array();
    }
}
