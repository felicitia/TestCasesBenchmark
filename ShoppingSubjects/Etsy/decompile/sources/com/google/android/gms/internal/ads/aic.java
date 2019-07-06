package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aic extends aam<aic> {
    private Integer a;
    private aih b;

    public aic() {
        this.a = null;
        this.b = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final aic a(aaj aaj) throws IOException {
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
                    this.b = new aih();
                }
                aaj.a((aar) this.b);
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
        return this.b != null ? a2 + aal.b(2, (aar) this.b) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(2, (aar) this.b);
        }
        super.a(aal);
    }
}
