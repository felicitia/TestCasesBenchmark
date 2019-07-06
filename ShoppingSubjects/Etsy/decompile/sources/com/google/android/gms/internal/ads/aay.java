package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aay extends aam<aay> {
    public aax[] a;
    private aaz b;
    private byte[] c;
    private byte[] d;
    private Integer e;

    public aay() {
        this.b = null;
        this.a = aax.b();
        this.c = null;
        this.d = null;
        this.e = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.b != null) {
            a2 += aal.b(1, (aar) this.b);
        }
        if (this.a != null && this.a.length > 0) {
            for (aax aax : this.a) {
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
        return this.e != null ? a2 + aal.b(5, this.e.intValue()) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                if (this.b == null) {
                    this.b = new aaz();
                }
                aaj.a((aar) this.b);
            } else if (a2 == 18) {
                int a3 = aau.a(aaj, 18);
                int length = this.a == null ? 0 : this.a.length;
                aax[] aaxArr = new aax[(a3 + length)];
                if (length != 0) {
                    System.arraycopy(this.a, 0, aaxArr, 0, length);
                }
                while (length < aaxArr.length - 1) {
                    aaxArr[length] = new aax();
                    aaj.a((aar) aaxArr[length]);
                    aaj.a();
                    length++;
                }
                aaxArr[length] = new aax();
                aaj.a((aar) aaxArr[length]);
                this.a = aaxArr;
            } else if (a2 == 26) {
                this.c = aaj.f();
            } else if (a2 == 34) {
                this.d = aaj.f();
            } else if (a2 == 40) {
                this.e = Integer.valueOf(aaj.c());
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.b != null) {
            aal.a(1, (aar) this.b);
        }
        if (this.a != null && this.a.length > 0) {
            for (aax aax : this.a) {
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
        super.a(aal);
    }
}
