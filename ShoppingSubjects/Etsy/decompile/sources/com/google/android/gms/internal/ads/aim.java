package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aim extends aam<aim> {
    private aii a;
    private Integer b;
    private ail c;
    private aih d;

    public aim() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final aim a(aaj aaj) throws IOException {
        aar aar;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                if (this.a == null) {
                    this.a = new aii();
                }
                aar = this.a;
            } else if (a2 == 16) {
                int j = aaj.j();
                try {
                    this.b = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
            } else if (a2 == 26) {
                if (this.c == null) {
                    this.c = new ail();
                }
                aar = this.c;
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
        if (this.b != null) {
            a2 += aal.b(2, this.b.intValue());
        }
        if (this.c != null) {
            a2 += aal.b(3, (aar) this.c);
        }
        return this.d != null ? a2 + aal.b(4, (aar) this.d) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, (aar) this.a);
        }
        if (this.b != null) {
            aal.a(2, this.b.intValue());
        }
        if (this.c != null) {
            aal.a(3, (aar) this.c);
        }
        if (this.d != null) {
            aal.a(4, (aar) this.d);
        }
        super.a(aal);
    }
}
