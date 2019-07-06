package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

public final class un implements qf {
    private static final byte[] a = new byte[0];
    private final up b;
    private final String c;
    private final byte[] d;
    private final zzayw e;
    private final ul f;

    public un(ECPublicKey eCPublicKey, byte[] bArr, String str, zzayw zzayw, ul ulVar) throws GeneralSecurityException {
        ur.a(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.b = new up(eCPublicKey);
        this.d = bArr;
        this.c = str;
        this.e = zzayw;
        this.f = ulVar;
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        uq a2 = this.b.a(this.c, this.d, bArr2, this.f.a(), this.e);
        byte[] a3 = this.f.a(a2.b()).a(bArr, a);
        byte[] a4 = a2.a();
        return ByteBuffer.allocate(a4.length + a3.length).put(a4).put(a3).array();
    }
}
