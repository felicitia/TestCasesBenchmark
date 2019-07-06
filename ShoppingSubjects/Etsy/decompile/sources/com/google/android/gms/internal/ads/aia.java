package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aia extends aam<aia> {
    private ahy a;
    private aig[] b;
    private Integer c;
    private aih d;

    public aia() {
        this.a = null;
        this.b = aig.b();
        this.c = null;
        this.d = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final aia a(aaj aaj) throws IOException {
        aar aar;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                if (this.a == null) {
                    this.a = new ahy();
                }
                aar = this.a;
            } else if (a2 == 18) {
                int a3 = aau.a(aaj, 18);
                int length = this.b == null ? 0 : this.b.length;
                aig[] aigArr = new aig[(a3 + length)];
                if (length != 0) {
                    System.arraycopy(this.b, 0, aigArr, 0, length);
                }
                while (length < aigArr.length - 1) {
                    aigArr[length] = new aig();
                    aaj.a((aar) aigArr[length]);
                    aaj.a();
                    length++;
                }
                aigArr[length] = new aig();
                aaj.a((aar) aigArr[length]);
                this.b = aigArr;
            } else if (a2 == 24) {
                int j = aaj.j();
                try {
                    this.c = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
            } else if (a2 == 34) {
                if (this.d == null) {
                    this.d = new aih();
                }
                aar = this.d;
            } else if (!super.a(aaj, a2)) {
                return this;
            }
            aaj.a(aar);
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, (aar) this.a);
        }
        if (this.b != null && this.b.length > 0) {
            for (aig aig : this.b) {
                if (aig != null) {
                    a2 += aal.b(2, (aar) aig);
                }
            }
        }
        if (this.c != null) {
            a2 += aal.b(3, this.c.intValue());
        }
        return this.d != null ? a2 + aal.b(4, (aar) this.d) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, (aar) this.a);
        }
        if (this.b != null && this.b.length > 0) {
            for (aig aig : this.b) {
                if (aig != null) {
                    aal.a(2, (aar) aig);
                }
            }
        }
        if (this.c != null) {
            aal.a(3, this.c.intValue());
        }
        if (this.d != null) {
            aal.a(4, (aar) this.d);
        }
        super.a(aal);
    }
}
