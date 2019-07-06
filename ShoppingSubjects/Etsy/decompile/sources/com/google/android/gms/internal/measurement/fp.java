package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fp extends e<fp> {
    private static volatile fp[] g;
    public String c;
    public Boolean d;
    public Boolean e;
    public Integer f;

    public fp() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fp[] e() {
        if (g == null) {
            synchronized (i.b) {
                if (g == null) {
                    g = new fp[0];
                }
            }
        }
        return g;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, this.c);
        }
        if (this.d != null) {
            this.d.booleanValue();
            a += d.b(2) + 1;
        }
        if (this.e != null) {
            this.e.booleanValue();
            a += d.b(3) + 1;
        }
        return this.f != null ? a + d.b(4, this.f.intValue()) : a;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 10) {
                this.c = cVar.c();
            } else if (a == 16) {
                this.d = Boolean.valueOf(cVar.b());
            } else if (a == 24) {
                this.e = Boolean.valueOf(cVar.b());
            } else if (a == 32) {
                this.f = Integer.valueOf(cVar.d());
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c);
        }
        if (this.d != null) {
            dVar.a(2, this.d.booleanValue());
        }
        if (this.e != null) {
            dVar.a(3, this.e.booleanValue());
        }
        if (this.f != null) {
            dVar.a(4, this.f.intValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fp)) {
            return false;
        }
        fp fpVar = (fp) obj;
        if (this.c == null) {
            if (fpVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fpVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fpVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fpVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fpVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fpVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fpVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fpVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fpVar.a == null || fpVar.a.b() : this.a.equals(fpVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
