package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aba extends aam<aba> {
    private abb a;
    private aax[] b;
    private byte[] c;
    private byte[] d;
    private Integer e;
    private byte[] f;

    public aba() {
        this.a = null;
        this.b = aax.b();
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, (aar) this.a);
        }
        if (this.b != null && this.b.length > 0) {
            for (aax aax : this.b) {
                if (aax != null) {
                    a2 += aal.b(2, (aar) aax);
                }
            }
        }
        if (this.c != null) {
            a2 += aal.b(3, this.c);
        }
        if (this.d != null) {
            a2 += aal.b(4, this.d);
        }
        if (this.e != null) {
            a2 += aal.b(5, this.e.intValue());
        }
        return this.f != null ? a2 + aal.b(6, this.f) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                if (this.a == null) {
                    this.a = new abb();
                }
                aaj.a((aar) this.a);
            } else if (a2 == 18) {
                int a3 = aau.a(aaj, 18);
                int length = this.b == null ? 0 : this.b.length;
                aax[] aaxArr = new aax[(a3 + length)];
                if (length != 0) {
                    System.arraycopy(this.b, 0, aaxArr, 0, length);
                }
                while (length < aaxArr.length - 1) {
                    aaxArr[length] = new aax();
                    aaj.a((aar) aaxArr[length]);
                    aaj.a();
                    length++;
                }
                aaxArr[length] = new aax();
                aaj.a((aar) aaxArr[length]);
                this.b = aaxArr;
            } else if (a2 == 26) {
                this.c = aaj.f();
            } else if (a2 == 34) {
                this.d = aaj.f();
            } else if (a2 == 40) {
                this.e = Integer.valueOf(aaj.c());
            } else if (a2 == 50) {
                this.f = aaj.f();
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, (aar) this.a);
        }
        if (this.b != null && this.b.length > 0) {
            for (aax aax : this.b) {
                if (aax != null) {
                    aal.a(2, (aar) aax);
                }
            }
        }
        if (this.c != null) {
            aal.a(3, this.c);
        }
        if (this.d != null) {
            aal.a(4, this.d);
        }
        if (this.e != null) {
            aal.a(5, this.e.intValue());
        }
        if (this.f != null) {
            aal.a(6, this.f);
        }
        super.a(aal);
    }
}
