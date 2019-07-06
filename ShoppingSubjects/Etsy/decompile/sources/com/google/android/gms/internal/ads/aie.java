package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aie extends aam<aie> {
    private Integer a;
    private Integer b;

    public aie() {
        this.a = null;
        this.b = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a.intValue());
        }
        return this.b != null ? a2 + aal.b(2, this.b.intValue()) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                this.a = Integer.valueOf(aaj.g());
            } else if (a2 == 16) {
                this.b = Integer.valueOf(aaj.g());
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(2, this.b.intValue());
        }
        super.a(aal);
    }
}
