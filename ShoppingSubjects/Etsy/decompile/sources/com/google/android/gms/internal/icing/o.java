package com.google.android.gms.internal.icing;

final class o {
    private final zzbu a;
    private final byte[] b;

    private o(int i) {
        this.b = new byte[i];
        this.a = zzbu.a(this.b);
    }

    /* synthetic */ o(int i, l lVar) {
        this(i);
    }

    public final zzbi a() {
        if (this.a.a() == 0) {
            return new zzbp(this.b);
        }
        throw new IllegalStateException("Did not write as much data as expected.");
    }

    public final zzbu b() {
        return this.a;
    }
}
