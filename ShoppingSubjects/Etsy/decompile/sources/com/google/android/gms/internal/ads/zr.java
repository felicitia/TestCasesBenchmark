package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zr extends aam<zr> {
    public byte[] a;
    public byte[] b;
    public byte[] c;
    public byte[] d;

    public zr() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a);
        }
        if (this.b != null) {
            a2 += aal.b(2, this.b);
        }
        if (this.c != null) {
            a2 += aal.b(3, this.c);
        }
        return this.d != null ? a2 + aal.b(4, this.d) : a2;
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
            } else if (a2 == 26) {
                this.c = aaj.f();
            } else if (a2 == 34) {
                this.d = aaj.f();
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a);
        }
        if (this.b != null) {
            aal.a(2, this.b);
        }
        if (this.c != null) {
            aal.a(3, this.c);
        }
        if (this.d != null) {
            aal.a(4, this.d);
        }
        super.a(aal);
    }
}
