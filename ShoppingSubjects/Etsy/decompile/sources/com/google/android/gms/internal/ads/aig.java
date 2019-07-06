package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aig extends aam<aig> {
    private static volatile aig[] a;
    private String b;
    private Integer c;
    private aih d;

    public aig() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final aig a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                this.b = aaj.e();
            } else if (a2 == 16) {
                int j = aaj.j();
                try {
                    this.c = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
            } else if (a2 == 26) {
                if (this.d == null) {
                    this.d = new aih();
                }
                aaj.a((aar) this.d);
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public static aig[] b() {
        if (a == null) {
            synchronized (aaq.b) {
                if (a == null) {
                    a = new aig[0];
                }
            }
        }
        return a;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.b != null) {
            a2 += aal.b(1, this.b);
        }
        if (this.c != null) {
            a2 += aal.b(2, this.c.intValue());
        }
        return this.d != null ? a2 + aal.b(3, (aar) this.d) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.b != null) {
            aal.a(1, this.b);
        }
        if (this.c != null) {
            aal.a(2, this.c.intValue());
        }
        if (this.d != null) {
            aal.a(3, (aar) this.d);
        }
        super.a(aal);
    }
}
