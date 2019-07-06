package com.google.android.gms.internal.icing;

final class s extends q {
    private final byte[] a;
    private final boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;

    private s(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.g = Integer.MAX_VALUE;
        this.a = bArr;
        this.c = i2 + i;
        this.e = i;
        this.f = this.e;
        this.b = z;
    }

    public final int a() {
        return this.e - this.f;
    }

    public final int a(int i) throws zzcs {
        if (i < 0) {
            throw new zzcs("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int a2 = i + a();
        int i2 = this.g;
        if (a2 > i2) {
            throw new zzcs("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        this.g = a2;
        this.c += this.d;
        int i3 = this.c - this.f;
        if (i3 > this.g) {
            this.d = i3 - this.g;
            this.c -= this.d;
            return i2;
        }
        this.d = 0;
        return i2;
    }
}
