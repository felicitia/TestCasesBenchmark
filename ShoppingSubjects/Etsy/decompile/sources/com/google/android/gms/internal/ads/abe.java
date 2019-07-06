package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class abe extends aam<abe> {
    public String a;
    public Long b;
    public Boolean c;

    public abe() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a);
        }
        if (this.b != null) {
            a2 += aal.d(2, this.b.longValue());
        }
        if (this.c == null) {
            return a2;
        }
        this.c.booleanValue();
        return a2 + aal.b(3) + 1;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 10) {
                this.a = aaj.e();
            } else if (a2 == 16) {
                this.b = Long.valueOf(aaj.b());
            } else if (a2 == 24) {
                this.c = Boolean.valueOf(aaj.d());
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
            aal.b(2, this.b.longValue());
        }
        if (this.c != null) {
            aal.a(3, this.c.booleanValue());
        }
        super.a(aal);
    }
}
