package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahw extends aam<ahw> {
    private Integer a;
    private aii b;
    private String c;
    private String d;

    public ahw() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ahw a(aaj aaj) throws IOException {
        int g;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 40) {
                try {
                    g = aaj.g();
                    if (g < 0 || g > 2) {
                        StringBuilder sb = new StringBuilder(40);
                        sb.append(g);
                        sb.append(" is not a valid enum Platform");
                    } else {
                        this.a = Integer.valueOf(g);
                    }
                } catch (IllegalArgumentException unused) {
                    aaj.e(aaj.j());
                    a(aaj, a2);
                }
            } else if (a2 == 50) {
                if (this.b == null) {
                    this.b = new aii();
                }
                aaj.a((aar) this.b);
            } else if (a2 == 58) {
                this.c = aaj.e();
            } else if (a2 == 66) {
                this.d = aaj.e();
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append(g);
        sb2.append(" is not a valid enum Platform");
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(5, this.a.intValue());
        }
        if (this.b != null) {
            a2 += aal.b(6, (aar) this.b);
        }
        if (this.c != null) {
            a2 += aal.b(7, this.c);
        }
        return this.d != null ? a2 + aal.b(8, this.d) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(5, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(6, (aar) this.b);
        }
        if (this.c != null) {
            aal.a(7, this.c);
        }
        if (this.d != null) {
            aal.a(8, this.d);
        }
        super.a(aal);
    }
}
