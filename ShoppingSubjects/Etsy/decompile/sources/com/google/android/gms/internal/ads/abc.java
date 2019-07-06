package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class abc extends aam<abc> {
    public Integer a;
    public String b;
    public byte[] c;

    public abc() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final abc a(aaj aaj) throws IOException {
        int c2;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                try {
                    c2 = aaj.c();
                    if (c2 < 0 || c2 > 1) {
                        StringBuilder sb = new StringBuilder(36);
                        sb.append(c2);
                        sb.append(" is not a valid enum Type");
                    } else {
                        this.a = Integer.valueOf(c2);
                    }
                } catch (IllegalArgumentException unused) {
                    aaj.e(aaj.j());
                    a(aaj, a2);
                }
            } else if (a2 == 18) {
                this.b = aaj.e();
            } else if (a2 == 26) {
                this.c = aaj.f();
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(36);
        sb2.append(c2);
        sb2.append(" is not a valid enum Type");
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a.intValue());
        }
        if (this.b != null) {
            a2 += aal.b(2, this.b);
        }
        return this.c != null ? a2 + aal.b(3, this.c) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(2, this.b);
        }
        if (this.c != null) {
            aal.a(3, this.c);
        }
        super.a(aal);
    }
}
