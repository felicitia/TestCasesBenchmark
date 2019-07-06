package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahr extends aam<ahr> {
    private static volatile ahr[] a;
    private Integer b;
    private aif c;

    public ahr() {
        this.b = null;
        this.c = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ahr a(aaj aaj) throws IOException {
        int g;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                try {
                    g = aaj.g();
                    if (g < 0 || g > 10) {
                        StringBuilder sb = new StringBuilder(44);
                        sb.append(g);
                        sb.append(" is not a valid enum AdFormatType");
                    } else {
                        this.b = Integer.valueOf(g);
                    }
                } catch (IllegalArgumentException unused) {
                    aaj.e(aaj.j());
                    a(aaj, a2);
                }
            } else if (a2 == 18) {
                if (this.c == null) {
                    this.c = new aif();
                }
                aaj.a((aar) this.c);
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(44);
        sb2.append(g);
        sb2.append(" is not a valid enum AdFormatType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public static ahr[] b() {
        if (a == null) {
            synchronized (aaq.b) {
                if (a == null) {
                    a = new ahr[0];
                }
            }
        }
        return a;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.b != null) {
            a2 += aal.b(1, this.b.intValue());
        }
        return this.c != null ? a2 + aal.b(2, (aar) this.c) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.b != null) {
            aal.a(1, this.b.intValue());
        }
        if (this.c != null) {
            aal.a(2, (aar) this.c);
        }
        super.a(aal);
    }
}
