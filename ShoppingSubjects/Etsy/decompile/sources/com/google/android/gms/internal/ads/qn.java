package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class qn<P> {
    private final P a;
    private final byte[] b;
    private final zzaxl c;
    private final zzayd d;

    public qn(P p, byte[] bArr, zzaxl zzaxl, zzayd zzayd) {
        this.a = p;
        this.b = Arrays.copyOf(bArr, bArr.length);
        this.c = zzaxl;
        this.d = zzayd;
    }

    public final P a() {
        return this.a;
    }

    public final byte[] b() {
        if (this.b == null) {
            return null;
        }
        return Arrays.copyOf(this.b, this.b.length);
    }
}
