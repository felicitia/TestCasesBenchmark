package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class xq extends aam<xq> {
    public Long a;
    public Long b;
    public Long c;
    private Long d;
    private Long e;

    public xq() {
        this.d = null;
        this.e = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.Z = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.d != null) {
            a2 += aal.d(1, this.d.longValue());
        }
        if (this.e != null) {
            a2 += aal.d(2, this.e.longValue());
        }
        if (this.a != null) {
            a2 += aal.d(3, this.a.longValue());
        }
        if (this.b != null) {
            a2 += aal.d(4, this.b.longValue());
        }
        return this.c != null ? a2 + aal.d(5, this.c.longValue()) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        while (true) {
            int a2 = aaj.a();
            if (a2 == 0) {
                return this;
            }
            if (a2 == 8) {
                this.d = Long.valueOf(aaj.h());
            } else if (a2 == 16) {
                this.e = Long.valueOf(aaj.h());
            } else if (a2 == 24) {
                this.a = Long.valueOf(aaj.h());
            } else if (a2 == 32) {
                this.b = Long.valueOf(aaj.h());
            } else if (a2 == 40) {
                this.c = Long.valueOf(aaj.h());
            } else if (!super.a(aaj, a2)) {
                return this;
            }
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.d != null) {
            aal.b(1, this.d.longValue());
        }
        if (this.e != null) {
            aal.b(2, this.e.longValue());
        }
        if (this.a != null) {
            aal.b(3, this.a.longValue());
        }
        if (this.b != null) {
            aal.b(4, this.b.longValue());
        }
        if (this.c != null) {
            aal.b(5, this.c.longValue());
        }
        super.a(aal);
    }
}
