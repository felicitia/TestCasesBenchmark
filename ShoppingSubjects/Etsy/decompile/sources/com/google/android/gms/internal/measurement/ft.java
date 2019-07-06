package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class ft extends e<ft> {
    private static volatile ft[] e;
    public Integer c;
    public Long d;

    public ft() {
        this.c = null;
        this.d = null;
        this.a = null;
        this.b = -1;
    }

    public static ft[] e() {
        if (e == null) {
            synchronized (i.b) {
                if (e == null) {
                    e = new ft[0];
                }
            }
        }
        return e;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, this.c.intValue());
        }
        return this.d != null ? a + d.c(2, this.d.longValue()) : a;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 8) {
                this.c = Integer.valueOf(cVar.d());
            } else if (a == 16) {
                this.d = Long.valueOf(cVar.e());
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c.intValue());
        }
        if (this.d != null) {
            dVar.b(2, this.d.longValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ft)) {
            return false;
        }
        ft ftVar = (ft) obj;
        if (this.c == null) {
            if (ftVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(ftVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (ftVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(ftVar.d)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? ftVar.a == null || ftVar.a.b() : this.a.equals(ftVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
