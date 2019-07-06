package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aio extends aam<aio> {
    private Integer a;
    private ail b;
    private aih c;

    public aio() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final aio a(aaj aaj) throws IOException {
        aar aar;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 != 8) {
                if (a2 == 18) {
                    if (this.b == null) {
                        this.b = new ail();
                    }
                    aar = this.b;
                } else if (a2 == 26) {
                    if (this.c == null) {
                        this.c = new aih();
                    }
                    aar = this.c;
                } else if (!super.a(aaj, a2)) {
                    return this;
                }
                aaj.a(aar);
            } else {
                int j = aaj.j();
                try {
                    this.a = Integer.valueOf(ahp.a(aaj.g()));
                } catch (IllegalArgumentException unused) {
                    aaj.e(j);
                    a(aaj, a2);
                }
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
        return this.c != null ? a2 + aal.b(3, (aar) this.c) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(2, (aar) this.b);
        }
        if (this.c != null) {
            aal.a(3, (aar) this.c);
        }
        super.a(aal);
    }
}
