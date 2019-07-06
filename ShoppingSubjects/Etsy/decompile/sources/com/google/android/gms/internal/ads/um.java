package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

public final class um implements qe {
    private static final byte[] a = new byte[0];
    private final ECPrivateKey b;
    private final uo c;
    private final String d;
    private final byte[] e;
    private final zzayw f;
    private final ul g;

    public um(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzayw zzayw, ul ulVar) throws GeneralSecurityException {
        this.b = eCPrivateKey;
        this.c = new uo(eCPrivateKey);
        this.e = bArr;
        this.d = str;
        this.f = zzayw;
        this.g = ulVar;
    }
}
