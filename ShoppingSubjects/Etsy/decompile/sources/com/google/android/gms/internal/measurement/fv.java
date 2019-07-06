package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fv extends e<fv> {
    private static volatile fv[] g;
    public String c;
    public String d;
    public Long e;
    public Double f;
    private Float h;

    public fv() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = null;
        this.f = null;
        this.a = null;
        this.b = -1;
    }

    public static fv[] e() {
        if (g == null) {
            synchronized (i.b) {
                if (g == null) {
                    g = new fv[0];
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
            a += d.b(2, this.d);
        }
        if (this.e != null) {
            a += d.c(3, this.e.longValue());
        }
        if (this.h != null) {
            this.h.floatValue();
            a += d.b(4) + 4;
        }
        if (this.f == null) {
            return a;
        }
        this.f.doubleValue();
        return a + d.b(5) + 8;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 10) {
                this.c = cVar.c();
            } else if (a == 18) {
                this.d = cVar.c();
            } else if (a == 24) {
                this.e = Long.valueOf(cVar.e());
            } else if (a == 37) {
                this.h = Float.valueOf(Float.intBitsToFloat(cVar.f()));
            } else if (a == 41) {
                this.f = Double.valueOf(Double.longBitsToDouble(cVar.g()));
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
            dVar.a(2, this.d);
        }
        if (this.e != null) {
            dVar.b(3, this.e.longValue());
        }
        if (this.h != null) {
            dVar.a(4, this.h.floatValue());
        }
        if (this.f != null) {
            dVar.a(5, this.f.doubleValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fv)) {
            return false;
        }
        fv fvVar = (fv) obj;
        if (this.c == null) {
            if (fvVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fvVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (fvVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(fvVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (fvVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fvVar.e)) {
            return false;
        }
        if (this.h == null) {
            if (fvVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(fvVar.h)) {
            return false;
        }
        if (this.f == null) {
            if (fvVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fvVar.f)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fvVar.a == null || fvVar.a.b() : this.a.equals(fvVar.a);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + (this.h == null ? 0 : this.h.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }
}
