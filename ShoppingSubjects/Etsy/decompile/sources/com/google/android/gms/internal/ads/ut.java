package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class ut implements py {
    private final vf a;
    private final qk b;
    private final int c;

    public ut(vf vfVar, qk qkVar, int i) {
        this.a = vfVar;
        this.b = qkVar;
        this.c = i;
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] a2 = this.a.a(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(8 * ((long) bArr2.length)).array(), 8);
        return ui.a(a2, this.b.a(ui.a(bArr2, a2, copyOf)));
    }
}
