package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aiq extends aam<aiq> {
    private Integer a;
    private ail b;
    private Integer c;
    private Integer d;
    private Integer e;
    private Long f;

    public aiq() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final aiq a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                int j = aaj.j();
                try {
                    this.a = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
            } else if (a2 == 18) {
                if (this.b == null) {
                    this.b = new ail();
                }
                aaj.a((aar) this.b);
            } else if (a2 == 24) {
                this.c = Integer.valueOf(aaj.g());
            } else if (a2 == 32) {
                this.d = Integer.valueOf(aaj.g());
            } else if (a2 == 40) {
                this.e = Integer.valueOf(aaj.g());
            } else if (a2 == 48) {
                this.f = Long.valueOf(aaj.h());
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a.intValue());
        }
        if (this.b != null) {
            a2 += aal.b(2, (aar) this.b);
        }
        if (this.c != null) {
            a2 += aal.b(3, this.c.intValue());
        }
        if (this.d != null) {
            a2 += aal.b(4, this.d.intValue());
        }
        if (this.e != null) {
            a2 += aal.b(5, this.e.intValue());
        }
        return this.f != null ? a2 + aal.c(6, this.f.longValue()) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(2, (aar) this.b);
        }
        if (this.c != null) {
            aal.a(3, this.c.intValue());
        }
        if (this.d != null) {
            aal.a(4, this.d.intValue());
        }
        if (this.e != null) {
            aal.a(5, this.e.intValue());
        }
        if (this.f != null) {
            aal.a(6, this.f.longValue());
        }
        super.a(aal);
    }
}
