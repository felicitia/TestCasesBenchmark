package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aih extends aam<aih> {
    private static volatile aih[] a;
    private Integer b;
    private Integer c;

    public aih() {
        this.b = null;
        this.c = null;
        this.Y = null;
        this.Z = -1;
    }

    public static aih[] b() {
        if (a == null) {
            synchronized (aaq.b) {
                if (a == null) {
                    a = new aih[0];
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
        return this.c != null ? a2 + aal.b(2, this.c.intValue()) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                this.b = Integer.valueOf(aaj.g());
            } else if (a2 == 16) {
                this.c = Integer.valueOf(aaj.g());
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.b != null) {
            aal.a(1, this.b.intValue());
        }
        if (this.c != null) {
            aal.a(2, this.c.intValue());
        }
        super.a(aal);
    }
}
