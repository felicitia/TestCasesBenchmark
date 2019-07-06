package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ail extends aam<ail> {
    private Integer a;

    public ail() {
        this.a = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ail a(aaj aaj) throws IOException {
        int g;
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                try {
                    g = aaj.g();
                    if (g < 0 || g > 3) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(g);
                        sb.append(" is not a valid enum VideoErrorCode");
                    } else {
                        this.a = Integer.valueOf(g);
                    }
                } catch (IllegalArgumentException unused) {
                    aaj.e(aaj.j());
                    a(aaj, a2);
                }
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(g);
        sb2.append(" is not a valid enum VideoErrorCode");
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        return this.a != null ? a2 + aal.b(1, this.a.intValue()) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        super.a(aal);
    }
}
