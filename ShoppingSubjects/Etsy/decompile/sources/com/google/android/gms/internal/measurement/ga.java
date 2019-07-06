package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class ga extends e<ga> {
    private static volatile ga[] h;
    public Long c;
    public String d;
    public String e;
    public Long f;
    public Double g;
    private Float i;

    public ga() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.i = null;
        this.g = null;
        this.a = null;
        this.b = -1;
    }

    public static ga[] e() {
        if (h == null) {
            synchronized (i.b) {
                if (h == null) {
                    h = new ga[0];
                }
            }
        }
        return h;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.c(1, this.c.longValue());
        }
        if (this.d != null) {
            a += d.b(2, this.d);
        }
        if (this.e != null) {
            a += d.b(3, this.e);
        }
        if (this.f != null) {
            a += d.c(4, this.f.longValue());
        }
        if (this.i != null) {
            this.i.floatValue();
            a += d.b(5) + 4;
        }
        if (this.g == null) {
            return a;
        }
        this.g.doubleValue();
        return a + d.b(6) + 8;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            if (a == 0) {
                return this;
            }
            if (a == 8) {
                this.c = Long.valueOf(cVar.e());
            } else if (a == 18) {
                this.d = cVar.c();
            } else if (a == 26) {
                this.e = cVar.c();
            } else if (a == 32) {
                this.f = Long.valueOf(cVar.e());
            } else if (a == 45) {
                this.i = Float.valueOf(Float.intBitsToFloat(cVar.f()));
            } else if (a == 49) {
                this.g = Double.valueOf(Double.longBitsToDouble(cVar.g()));
            } else if (!super.a(cVar, a)) {
                return this;
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.b(1, this.c.longValue());
        }
        if (this.d != null) {
            dVar.a(2, this.d);
        }
        if (this.e != null) {
            dVar.a(3, this.e);
        }
        if (this.f != null) {
            dVar.b(4, this.f.longValue());
        }
        if (this.i != null) {
            dVar.a(5, this.i.floatValue());
        }
        if (this.g != null) {
            dVar.a(6, this.g.doubleValue());
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ga)) {
            return false;
        }
        ga gaVar = (ga) obj;
        if (this.c == null) {
            if (gaVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(gaVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (gaVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(gaVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (gaVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(gaVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (gaVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(gaVar.f)) {
            return false;
        }
        if (this.i == null) {
            if (gaVar.i != null) {
                return false;
            }
        } else if (!this.i.equals(gaVar.i)) {
            return false;
        }
        if (this.g == null) {
            if (gaVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(gaVar.g)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? gaVar.a == null || gaVar.a.b() : this.a.equals(gaVar.a);
    }

    public final int hashCode() {
        int i2 = 0;
        int hashCode = (((((((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d == null ? 0 : this.d.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode())) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.i == null ? 0 : this.i.hashCode())) * 31) + (this.g == null ? 0 : this.g.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i2 = this.a.hashCode();
        }
        return hashCode + i2;
    }
}
