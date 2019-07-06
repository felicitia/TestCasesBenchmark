package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class abh extends aam<abh> {
    public byte[][] a;
    public byte[] b;
    public Integer c;
    private Integer d;

    public abh() {
        this.a = aau.d;
        this.b = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final abh a(aaj aaj) throws IOException {
        int i;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                int a3 = aau.a(aaj, 10);
                int length = this.a == null ? 0 : this.a.length;
                byte[][] bArr = new byte[(a3 + length)][];
                if (length != 0) {
                    System.arraycopy(this.a, 0, bArr, 0, length);
                }
                while (length < bArr.length - 1) {
                    bArr[length] = aaj.f();
                    aaj.a();
                    length++;
                }
                bArr[length] = aaj.f();
                this.a = bArr;
            } else if (a2 == 18) {
                this.b = aaj.f();
            } else if (a2 == 24) {
                i = aaj.j();
                this.d = Integer.valueOf(uw.b(aaj.g()));
            } else if (a2 == 32) {
                i = aaj.j();
                try {
                    this.c = Integer.valueOf(uw.c(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(i);
                    a(aaj, a2);
                }
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null && this.a.length > 0) {
            int i = 0;
            int i2 = 0;
            for (byte[] bArr : this.a) {
                if (bArr != null) {
                    i2++;
                    i += aal.b(bArr);
                }
            }
            a2 = a2 + i + (1 * i2);
        }
        if (this.b != null) {
            a2 += aal.b(2, this.b);
        }
        if (this.d != null) {
            a2 += aal.b(3, this.d.intValue());
        }
        return this.c != null ? a2 + aal.b(4, this.c.intValue()) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null && this.a.length > 0) {
            for (byte[] bArr : this.a) {
                if (bArr != null) {
                    aal.a(1, bArr);
                }
            }
        }
        if (this.b != null) {
            aal.a(2, this.b);
        }
        if (this.d != null) {
            aal.a(3, this.d.intValue());
        }
        if (this.c != null) {
            aal.a(4, this.c.intValue());
        }
        super.a(aal);
    }
}
