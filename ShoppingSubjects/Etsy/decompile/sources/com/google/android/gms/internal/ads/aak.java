package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aak extends aam<aak> {
    public Long a;
    private String b;
    private byte[] c;

    public aak() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.d(1, this.a.longValue());
        }
        if (this.b != null) {
            a2 += aal.b(3, this.b);
        }
        return this.c != null ? a2 + aal.b(4, this.c) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                this.a = Long.valueOf(aaj.h());
            } else if (a2 == 26) {
                this.b = aaj.e();
            } else if (a2 == 34) {
                this.c = aaj.f();
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.b(1, this.a.longValue());
        }
        if (this.b != null) {
            aal.a(3, this.b);
        }
        if (this.c != null) {
            aal.a(4, this.c);
        }
        super.a(aal);
    }
}
