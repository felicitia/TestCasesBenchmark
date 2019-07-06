package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aax extends aam<aax> {
    private static volatile aax[] c;
    public byte[] a;
    public byte[] b;

    public aax() {
        this.a = null;
        this.b = null;
        this.Y = null;
        this.Z = -1;
    }

    public static aax[] b() {
        if (c == null) {
            synchronized (aaq.b) {
                if (c == null) {
                    c = new aax[0];
                }
            }
        }
        return c;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a() + aal.b(1, this.a);
        return this.b != null ? a2 + aal.b(2, this.b) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                this.a = aaj.f();
            } else if (a2 == 18) {
                this.b = aaj.f();
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        aal.a(1, this.a);
        if (this.b != null) {
            aal.a(2, this.b);
        }
        super.a(aal);
    }
}
